package com.dicoding.salsahava.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mFragmentManager = supportFragmentManager
        val mListFragment = ListFragment()
        val fragment = mFragmentManager.findFragmentByTag(ListFragment::class.java.simpleName)

        if (fragment !is ListFragment) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mListFragment, ListFragment::class.java.simpleName)
                .commit()
        }
    }
}