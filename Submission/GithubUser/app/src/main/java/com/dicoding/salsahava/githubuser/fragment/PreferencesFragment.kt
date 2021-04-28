package com.dicoding.salsahava.githubuser.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.salsahava.githubuser.R
import com.dicoding.salsahava.githubuser.receiver.AlarmReceiver

class PreferencesFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var reminderKey: String
    private lateinit var languageKey: String
    private lateinit var reminderPreference: SwitchPreference
    private lateinit var languagePreference: Preference
    private lateinit var alarmReceiver: AlarmReceiver

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

        alarmReceiver = AlarmReceiver()
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        when (key) {
            reminderKey -> {
                val isStatusOn = sharedPreferences.getBoolean(reminderKey, false)
                if (isStatusOn) context?.let {
                    alarmReceiver.setRepeatingAlarm(
                        it,
                        "09:00",
                        "Here's your daily reminder to open me! :)"
                    )
                }
                else context?.let { alarmReceiver.cancelAlarm(it) }

                context?.let { alarmReceiver.isAlarmSet(it).toString() }?.let {
                    Log.d(
                        "isAlarmSet: ",
                        it
                    )
                }
            }
        }
    }
}