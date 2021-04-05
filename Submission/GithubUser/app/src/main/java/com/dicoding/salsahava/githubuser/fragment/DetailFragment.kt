package com.dicoding.salsahava.githubuser.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.salsahava.githubuser.User
import com.dicoding.salsahava.githubuser.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        var EXTRA_USER = "extra_user"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        manageData()
    }

    private fun manageData() {
        if (arguments != null) {
            val user = arguments?.getParcelable<User>(EXTRA_USER) as User

            Glide.with(this)
                .load(user.avatar)
                .into(binding.imgAvatar)

            binding.tvName.text = user.name
            binding.tvUsername.text = user.username
            binding.tvLocation.text = user.location
            binding.tvCompany.text = user.company
            binding.tvFollowers.text = user.followers
            binding.tvFollowing.text = user.following
            binding.tvRepositories.text = user.repositories

            (activity as AppCompatActivity).supportActionBar?.title = user.username
        }
    }
}