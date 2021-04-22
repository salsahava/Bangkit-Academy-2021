package com.dicoding.salsahava.githubuser.database

import android.provider.BaseColumns

internal class DatabaseContract {
    internal class FavoriteUserColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "favorite_user"
            const val _ID = "_id"
            const val USERNAME = "username"
            const val AVATAR_URL = "avatar_url"
            const val NAME = "name"
            const val COMPANY = "company"
            const val LOCATION = "location"
            const val FOLLOWERS = "followers"
            const val FOLLOWING ="following"
            const val REPOSITORY = "repository"
        }
    }
}