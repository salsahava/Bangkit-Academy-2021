package com.dicoding.salsahava.githubuser

import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.salsahava.githubuser.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepositories: IntArray
    private lateinit var dataCompany: Array<String>
    private lateinit var dataFollowers: IntArray
    private lateinit var dataFollowing: IntArray
    private lateinit var dataAvatar: TypedArray
    private var users = arrayListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvUsers.setHasFixedSize(true)

        prepare()
        addItem()

        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding.rvUsers.layoutManager = GridLayoutManager(activity, 2)
        val userAdapter = UserAdapter(users)
        binding.rvUsers.adapter = userAdapter
    }

    private fun prepare() {
        dataUsername = resources.getStringArray(R.array.username)
        dataName = resources.getStringArray(R.array.name)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepositories = resources.getIntArray(R.array.repository)
        dataCompany = resources.getStringArray(R.array.company)
        dataFollowers = resources.getIntArray(R.array.followers)
        dataFollowing = resources.getIntArray(R.array.following)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
    }

    private fun addItem() {
        for (position in dataUsername.indices) {
            val user = User(
                dataUsername[position],
                dataName[position],
                dataLocation[position],
                dataRepositories[position],
                dataCompany[position],
                dataFollowers[position],
                dataFollowing[position],
                dataAvatar.getResourceId(position, -1)
            )
            users.add(user)
        }
    }
}