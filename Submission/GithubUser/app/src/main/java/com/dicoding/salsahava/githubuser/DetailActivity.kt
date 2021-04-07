package com.dicoding.salsahava.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.salsahava.githubuser.adapter.PagerAdapter
import com.dicoding.salsahava.githubuser.api.UserData
import com.dicoding.salsahava.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_followers, R.string.tab_following)
        var EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        username?.let { showDetail(it) }
        showLoading(true)

        username?.let { showTabLayout(it) }

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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}