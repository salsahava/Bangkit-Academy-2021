package com.dicoding.salsahava.githubuser.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.salsahava.githubuser.fragment.FollowersFragment
import com.dicoding.salsahava.githubuser.fragment.FollowingFragment

class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    lateinit var username: String

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var tabFragment: Fragment? = null
        when (position) {
            0 -> tabFragment = FollowersFragment.newInstance(username)
            1 -> tabFragment = FollowingFragment.newInstance(username)
        }

        return tabFragment as Fragment
    }
}