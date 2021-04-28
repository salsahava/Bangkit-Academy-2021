package com.dicoding.salsahava.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.salsahava.githubuser.fragment.PreferencesFragment

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        supportFragmentManager.beginTransaction()
            .add(R.id.settings_holder, PreferencesFragment())
            .commit()

        supportActionBar?.title = getString(R.string.settings)
    }
}