package ru.daryasoft.fintracker.ui

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import ru.daryasoft.fintracker.R

/**
 * Фрагмент для настроек приложения.
 */
private const val IS_DIALOG_SHOWN_KEY = "isDialogShown"

class SettingsFragment : PreferenceFragmentCompat() {

    private var isDialogShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.title = getString(R.string.title_fragment_settings)
        initAbout(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_DIALOG_SHOWN_KEY, isDialogShown)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    private fun initAbout(savedInstanceState: Bundle?) {
        isDialogShown = savedInstanceState?.getBoolean(IS_DIALOG_SHOWN_KEY) ?: false
        if (isDialogShown) {
            showDialog()
        }

        preferenceManager.findPreference(getString(R.string.about_preference_key)).setOnPreferenceClickListener {
            showDialog()
            true
        }
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(context)
                .setView(R.layout.dialog_about)
                .create()
        dialog.setOnDismissListener { isDialogShown = false }
        dialog.setOnShowListener { isDialogShown = true }
        dialog.show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}
