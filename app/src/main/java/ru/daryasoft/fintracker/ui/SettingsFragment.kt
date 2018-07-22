package ru.daryasoft.fintracker.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.PreferenceFragmentCompat
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Currency
import android.widget.TextView
import android.widget.LinearLayout




class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.title = getString(R.string.title_fragment_settings)

        preferenceManager.findPreference(getString(R.string.about_preference_key)).setOnPreferenceClickListener {
            val adb = AlertDialog.Builder(context)
            adb.setView(layoutInflater.inflate(R.layout.fragment_about, null))
            adb.create().show()
            true
        }

        val defaultCurrencyPreference = preferenceManager.findPreference(getString(R.string.currency_list_preference_key))
        if (defaultCurrencyPreference is ListPreference) {
            defaultCurrencyPreference.summary = defaultCurrencyPreference.entry ?: Currency.RUB.toString()
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
