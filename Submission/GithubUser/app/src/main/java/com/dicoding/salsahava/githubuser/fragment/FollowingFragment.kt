package com.dicoding.salsahava.githubuser.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.salsahava.githubuser.R
import com.dicoding.salsahava.githubuser.adapter.UserAdapter
import com.dicoding.salsahava.githubuser.databinding.FragmentFollowingBinding
import com.dicoding.salsahava.githubuser.viewmodel.FollowingViewModel

class FollowingFragment : Fragment() {

    companion object {
        private val ARG_USERNAME = "username"

        fun newInstance(username: String): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    private lateinit var followingViewModel: FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollowing.setHasFixedSize(true)
        binding.rvFollowing.layoutManager = LinearLayoutManager(activity)

        val username = arguments?.getString(ARG_USERNAME)

        val adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        binding.rvFollowing.adapter = adapter

        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        showLoading(true)
        if (username != null) followingViewModel.setFollowingList(username)
        followingViewModel.getFollowing().observe(viewLifecycleOwner, { following ->
            if (following != null) {
                adapter.setFollowingList(following)
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