package com.dicoding.salsahava.githubuser

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items")
    val userItem: List<UserItem>
)

data class UserItem(
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)

data class User(
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String,
    val company: String,
    val location: String,
)

data class Follower(
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)

data class Following(
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)

