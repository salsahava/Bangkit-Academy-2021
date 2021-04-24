package com.dicoding.salsahava.consumerapp

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.salsahava.consumerapp.adapter.PagerAdapter
import com.dicoding.salsahava.consumerapp.api.UserData
import com.dicoding.salsahava.consumerapp.database.DatabaseContract
import com.dicoding.salsahava.consumerapp.database.DatabaseContract.FavoriteUserColumns.Companion.CONTENT_URI
import com.dicoding.salsahava.consumerapp.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var uriWithId: Uri
    private var username: String? = null
    private var avatarUrl: String? = null
    private var id: Int = 0

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_followers, R.string.tab_following)
        var EXTRA_ID = "extra_id"
        var EXTRA_USERNAME = "extra_username"
        var EXTRA_AVATAR_URL = "extra_avatar_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra(EXTRA_USERNAME)
        avatarUrl = intent.getStringExtra(EXTRA_AVATAR_URL)
        id = intent.getIntExtra(EXTRA_ID, 0)

        username?.let { showDetail(it) }
        showLoading(true)

        username?.let { showTabLayout(it) }

        var isFavoriteUser = false
        GlobalScope.launch(Dispatchers.Main) {
            val deferredFavUser = async(Dispatchers.IO) {
                uriWithId = Uri.parse("$CONTENT_URI/$id")
                val cursor = contentResolver.query(uriWithId, null, null, null, null)
                cursor
            }

            val user = deferredFavUser.await()
            if (user != null) {
                isFavoriteUser = if (user.count > 0) {
                    setFavoriteStatus(true)
                    true
                } else {
                    setFavoriteStatus(false)
                    false
                }
            }
        }

        binding.fabFav.setOnClickListener {
            isFavoriteUser = !isFavoriteUser
            if (isFavoriteUser) {
                setFavoriteStatus(true)
                val values = ContentValues()
                values.put(DatabaseContract.FavoriteUserColumns._ID, id)
                values.put(DatabaseContract.FavoriteUserColumns.USERNAME, username)
                values.put(DatabaseContract.FavoriteUserColumns.AVATAR_URL, avatarUrl)

                contentResolver.insert(CONTENT_URI, values)
                showToastMessage(getString(R.string.add_success, username))
            } else {
                setFavoriteStatus(false)
                val userDeleted = contentResolver.delete(uriWithId, null, null)
                Log.d("User Deleted: ", userDeleted.toString())
                showToastMessage(getString(R.string.delete_success, username))
            }
            setFavoriteStatus(isFavoriteUser)
        }

        supportActionBar?.title = username
    }

    private fun showDetail(username: String) {
        val githubService = UserData.create()
        githubService.getUser(username).enqueue(object : Callback<User> {
            override fun onResponse(call: retrofit2.Call<User>, response: Response<User>) {
                try {
                    val detailResponse = response.body()

                    Glide.with(this@DetailActivity)
                        .load(detailResponse?.avatarUrl)
                        .into(binding.imgAvatar)

                    binding.tvName.text = detailResponse?.name
                    binding.tvUsername.text = detailResponse?.username
                    binding.tvLocation.text = detailResponse?.location
                    binding.tvCompany.text = detailResponse?.company
                    binding.tvFollowers.text = detailResponse?.followers.toString()
                    binding.tvFollowing.text = detailResponse?.following.toString()
                    binding.tvRepos.text = detailResponse?.repos.toString()

                    showLoading(false)
                } catch (e: Exception) {
                    Log.d("onResponse: Exception", e.message.toString())
                }
            }

            override fun onFailure(call: retrofit2.Call<User>, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    private fun showTabLayout(uname: String) {
        val pagerAdapter = PagerAdapter(this)
        pagerAdapter.username = uname
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = pagerAdapter

        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun setFavoriteStatus(isFavorite: Boolean) {
        if (isFavorite) binding.fabFav.setImageResource(R.drawable.ic_baseline_favorite_white_24)
        else binding.fabFav.setImageResource(R.drawable.ic_baseline_favorite_border_white_24)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}