package com.dicoding.salsahava.githubuser.helper

import android.database.Cursor
import com.dicoding.salsahava.githubuser.UserItem
import com.dicoding.salsahava.githubuser.database.DatabaseContract

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<UserItem> {
        val favUserList = ArrayList<UserItem>()

        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns._ID))
                val username =
                    getString(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.USERNAME))
                val avatarUrl =
                    getString(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.AVATAR_URL))

                favUserList.add(UserItem(id, username, avatarUrl))
            }
        }
        return favUserList
    }
}