package com.dicoding.salsahava.githubuser.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.salsahava.githubuser.R

class PreferencesFragment :  PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var reminderKey: String
    private lateinit var languageKey: String
    private lateinit var reminderPreference: SwitchPreference
    private lateinit var languagePreference: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        reminderKey = getString(R.string.key_reminder)
        languageKey = getString(R.string.key_language)
        reminderPreference = findPreference<SwitchPreference>(reminderKey) as SwitchPreference
        languagePreference = findPreference<Preference>(languageKey) as Preference

        languagePreference.setOnPreferenceClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)

            true
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            reminderKey -> {

            }
        }
    }
}