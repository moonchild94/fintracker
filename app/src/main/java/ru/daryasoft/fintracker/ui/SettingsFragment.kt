package ru.daryasoft.fintracker.ui

import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.PreferenceFragmentCompat
import ru.daryasoft.fintracker.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val defaultCurrencyPreference = preferenceManager.findPreference(getString(R.string.currency_list_preference_key))
        if (defaultCurrencyPreference is ListPreference) {
            defaultCurrencyPreference.summary = defaultCurrencyPreference.entry
        }
        defaultCurrencyPreference
                .setOnPreferenceChangeListener { preference, newValue ->
                    preference.summary = newValue.toString()
                    true
                }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}
