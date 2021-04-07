package com.dicoding.salsahava.githubuser.api

import com.dicoding.salsahava.githubuser.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.GITHUB_API_TOKEN}")
    fun findUser(@Query("q") username: String): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.GITHUB_API_TOKEN}")
    fun getUser(@Path("username") username: String): Call<User>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.GITHUB_API_TOKEN}")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<Follower>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.GITHUB_API_TOKEN}")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<Following>>

}