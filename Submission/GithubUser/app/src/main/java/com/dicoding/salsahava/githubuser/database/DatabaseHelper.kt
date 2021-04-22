package com.dicoding.salsahava.githubuser.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.salsahava.githubuser.database.DatabaseContract.FavoriteUserColumns.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "dbfavuser"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_FAV_USER = "CREATE TABLE $TABLE_NAME" +
                "(${DatabaseContract.FavoriteUserColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DatabaseContract.FavoriteUserColumns.USERNAME} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteUserColumns.AVATAR_URL} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteUserColumns.NAME} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteUserColumns.COMPANY} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteUserColumns.LOCATION} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteUserColumns.FOLLOWERS} INTEGER NOT NULL," +
                "${DatabaseContract.FavoriteUserColumns.FOLLOWING} INTEGER NOT NULL," +
                "${DatabaseContract.FavoriteUserColumns.REPOSITORY} INTEGER NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_FAV_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}