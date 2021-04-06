package com.dicoding.salsahava.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.salsahava.githubuser.Follower
import com.dicoding.salsahava.githubuser.api.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<Follower>>()

    fun setFollowersList(username: String) {
        val githubService = UserData.create()
        githubService.getFollowers(username).enqueue(object : Callback<ArrayList<Follower>> {
            override fun onResponse(
                call: Call<ArrayList<Follower>>,
                response: Response<ArrayList<Follower>>
            ) {
                try {
                    val followers = response.body()
                    listFollowers.postValue(followers)
                } catch (e: Exception) {
                    Log.d("onResponse: Exception", e.message.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<Follower>>, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    fun getFollowers(): LiveData<ArrayList<Follower>> {
        return listFollowers
    }
}