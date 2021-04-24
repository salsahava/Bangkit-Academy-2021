package com.dicoding.salsahava.githubuser

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SearchResponse(
    @SerializedName("items")
    val userItem: ArrayList<UserItem>
)

@Parcelize
data class UserItem(
    var id: Int,
    @SerializedName("login")
    var username: String,
    @SerializedName("avatar_url")
    var avatarUrl: String
) : Parcelable

data class User(
    val id: Int,
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String,
    val company: String,
    val location: String,
    val followers: Int,
    val following: Int,
    @SerializedName("public_repos")
    val repos: Int
)

data class Follower(
    val id: Int,
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)

data class Following(
    val id: Int,
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)

