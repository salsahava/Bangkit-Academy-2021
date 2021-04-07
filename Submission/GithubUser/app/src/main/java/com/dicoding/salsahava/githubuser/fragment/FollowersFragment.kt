package com.dicoding.salsahava.githubuser.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.salsahava.githubuser.adapter.UserAdapter
import com.dicoding.salsahava.githubuser.databinding.FragmentFollowersBinding
import com.dicoding.salsahava.githubuser.viewmodel.FollowersViewModel

class FollowersFragment : Fragment() {

    companion object {
        private val ARG_USERNAME = "username"

        fun newInstance(username: String): FollowersFragment {
            val fragment = FollowersFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!

    private lateinit var followersViewModel: FollowersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollowers.setHasFixedSize(true)
        binding.rvFollowers.layoutManager = LinearLayoutManager(activity)

        val username = arguments?.getString(ARG_USERNAME)

        val adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        binding.rvFollowers.adapter = adapter

        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        showLoading(true)
        if (username != null) followersViewModel.setFollowersList(username)
        followersViewModel.getFollowers().observe(viewLifecycleOwner, { followers ->
            if (followers != null) {
                adapter.setFollowerList(followers)
                showLoading(false)
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