package com.dicoding.salsahava.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.salsahava.githubuser.databinding.ActivityFavoriteBinding
import com.dicoding.salsahava.githubuser.viewmodel.UserListViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var userListViewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}