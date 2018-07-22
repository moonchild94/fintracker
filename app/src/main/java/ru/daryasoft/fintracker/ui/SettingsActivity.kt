package ru.daryasoft.fintracker.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.daryasoft.fintracker.R

/**
 * Активити для экрана настроек.
 */
class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        addSingleFragment(SettingsFragment.newInstance(), R.id.fragment_container)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
