package com.dicoding.salsahava.consumerapp.api

import com.dicoding.salsahava.consumerapp.Follower
import com.dicoding.salsahava.consumerapp.Following
import com.dicoding.salsahava.consumerapp.SearchResponse
import com.dicoding.salsahava.consumerapp.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    fun findUser(@Query("q") username: String): Call<SearchResponse>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<Follower>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<Following>>

}