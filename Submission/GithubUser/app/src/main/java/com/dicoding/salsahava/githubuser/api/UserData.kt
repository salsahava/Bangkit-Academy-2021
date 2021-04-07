package com.dicoding.salsahava.githubuser.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserData {
    fun create(): GithubService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()

        return retrofit.create(GithubService::class.java)
    }
}