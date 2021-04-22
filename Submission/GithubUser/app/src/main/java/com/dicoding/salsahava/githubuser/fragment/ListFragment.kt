package com.dicoding.salsahava.githubuser.fragment

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.salsahava.githubuser.*
import com.dicoding.salsahava.githubuser.adapter.UserAdapter
import com.dicoding.salsahava.githubuser.databinding.FragmentListBinding
import com.dicoding.salsahava.githubuser.viewmodel.UserListViewModel

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListViewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = LinearLayoutManager(activity)

        val userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()
        binding.rvUsers.adapter = userAdapter

        userListViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserListViewModel::class.java)

        searchUser()

        userListViewModel.getUsers().observe(viewLifecycleOwner, { userItems ->
            if (userItems != null) {
                userAdapter.setUserList(userItems)
                showLoading(false)
            }
        })

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserItem, position: Int) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME, data.username)
                startActivity(intent)
            }
        })
    }

    private fun searchUser() {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.svUser

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showLoading(true)
                userListViewModel.setUserList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}