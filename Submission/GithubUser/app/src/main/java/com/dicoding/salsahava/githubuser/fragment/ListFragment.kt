package com.dicoding.salsahava.githubuser.fragment

import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.salsahava.githubuser.*
import com.dicoding.salsahava.githubuser.UserData.create
import com.dicoding.salsahava.githubuser.databinding.FragmentListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepositories: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataAvatar: TypedArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvUsers.setHasFixedSize(true)

        showLoading(true)

        val githubService = UserData.create()
        githubService.findUser("salsa").enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                try {
                    val userItems = response.body()?.userItem
                    showLoading(false)
                    showRecyclerList(view, userItems)
                } catch (e: Exception) {
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SearchResponse>, error: Throwable) {
                Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    private fun showRecyclerList(view: View, userItems: List<UserItem>?) {
        binding.rvUsers.layoutManager = LinearLayoutManager(activity)
        val userAdapter = userItems?.let { UserAdapter(it) }
        binding.rvUsers.adapter = userAdapter

        userAdapter?.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserItem) {

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


//    private fun showRecyclerList(view: View) {
//        binding.rvUsers.layoutManager = LinearLayoutManager(activity)
//        val userAdapter = UserAdapter(users)
//        binding.rvUsers.adapter = userAdapter
//
//        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: User) {
//                val mBundle = Bundle()
//                mBundle.putParcelable(DetailFragment.EXTRA_USER, data)
//
//                view.findNavController().navigate(R.id.action_listFragment_to_detailFragment, mBundle)
//            }
//        })
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}