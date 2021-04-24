package com.dicoding.salsahava.githubuser.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dicoding.salsahava.githubuser.database.DatabaseContract.AUTHORITY
import com.dicoding.salsahava.githubuser.database.DatabaseContract.FavoriteUserColumns.Companion.CONTENT_URI
import com.dicoding.salsahava.githubuser.database.DatabaseContract.FavoriteUserColumns.Companion.TABLE_NAME
import com.dicoding.salsahava.githubuser.database.FavUserHelper

class FavUserProvider : ContentProvider() {

    companion object {
        private const val FAV_USER = 1
        private const val FAV_USER_ID = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var favUserHelper: FavUserHelper

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, FAV_USER)

            uriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", FAV_USER_ID)
        }
    }

    override fun onCreate(): Boolean {
        favUserHelper = FavUserHelper.getInstance(context as Context)
        favUserHelper.open()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            FAV_USER -> favUserHelper.queryAll()
            FAV_USER_ID -> favUserHelper.queryById(uri.lastPathSegment.toString())
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val added: Long = when (FAV_USER) {
            uriMatcher.match(uri) -> favUserHelper.insert(values)
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return Uri.parse("$CONTENT_URI/$added")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val updated: Int = when (FAV_USER_ID) {
            uriMatcher.match(uri) -> favUserHelper.update(uri.lastPathSegment.toString(), values)
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return updated
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val deleted: Int = when (FAV_USER_ID) {
            uriMatcher.match(uri) -> favUserHelper.deleteById(uri.lastPathSegment.toString())
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return deleted
    }
}