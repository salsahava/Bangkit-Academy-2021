package com.dicoding.salsahava.consumerapp.helper

import android.database.Cursor
import com.dicoding.salsahava.consumerapp.UserItem
import com.dicoding.salsahava.consumerapp.database.DatabaseContract

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