package com.dicoding.salsahava.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.salsahava.githubuser.SearchResponse
import com.dicoding.salsahava.githubuser.api.UserData
import com.dicoding.salsahava.githubuser.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListViewModel : ViewModel() {
    val listUser = MutableLiveData<ArrayList<UserItem>>()

    fun setUserList(user: String) {
        val githubService = UserData.create()
        githubService.findUser(user).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                try {
                    val userItems = response.body()?.userItem
                    listUser.postValue(userItems)
                } catch (e: Exception) {
                    Log.d("onResponse: Exception", e.message.toString())
                }
            }

            override fun onFailure(call: Call<SearchResponse>, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    fun getUsers(): LiveData<ArrayList<UserItem>> {
        return listUser
    }
}