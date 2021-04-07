package com.dicoding.salsahava.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.salsahava.githubuser.Following
import com.dicoding.salsahava.githubuser.api.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class FollowingViewModel : ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<Following>>()

    fun setFollowingList(username: String) {
        val githubService = UserData.create()
        githubService.getFollowing(username).enqueue(object : Callback<ArrayList<Following>> {
            override fun onResponse(
                call: Call<ArrayList<Following>>,
                response: Response<ArrayList<Following>>
            ) {
                try {
                    val following = response.body()
                    listFollowing.postValue(following)
                } catch (e: Exception) {
                    Log.d("onResponse: Exception", e.message.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<Following>>, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    fun getFollowing(): LiveData<ArrayList<Following>> {
        return listFollowing
    }
}