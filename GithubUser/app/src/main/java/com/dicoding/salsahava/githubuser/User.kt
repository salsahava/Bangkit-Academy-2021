package com.dicoding.salsahava.githubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var username: String,
    var name: String,
    var location: String,
    var repositories: String,
    var company: String,
    var followers: String,
    var following: String,
    var avatar: Int
) : Parcelable