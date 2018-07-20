package ru.daryasoft.fintracker.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import ru.daryasoft.fintracker.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        changeFragment(AboutFragment.newInstance(), R.id.fragment_container)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
