package ru.daryasoft.fintracker.ui

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.ui.account.AccountsFragment
import ru.daryasoft.fintracker.ui.transaction.AddTransactionFragment
import ru.daryasoft.fintracker.ui.transaction.AddTransactionListener

class MainActivity : DaggerAppCompatActivity(), AddTransactionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSideMenu()
        if (savedInstanceState == null) {
            replaceFragment(MainFragment.newInstance(), R.id.main_fragment_container)
        }
    }

    override fun onAddTransactionOpen() {
        replaceFragment(AddTransactionFragment.newInstance(), R.id.main_fragment_container)
    }

    override fun onAddTransactionComplete() {
        replaceFragment(MainFragment.newInstance(), R.id.main_fragment_container)
    }

    private fun initSideMenu() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.title_fragment_balance, R.string.title_fragment_operation)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawer_layout.closeDrawers()

            when (menuItem.itemId) {
                R.id.main_page -> replaceFragment(MainFragment.newInstance(), R.id.main_fragment_container)
                R.id.accounts -> replaceFragment(AccountsFragment.newInstance(), R.id.main_fragment_container)
                R.id.categories -> replaceFragment(SettingsFragment.newInstance(), R.id.main_fragment_container)
                R.id.settings -> replaceFragment(SettingsFragment.newInstance(), R.id.main_fragment_container)
                else -> throw IllegalArgumentException()
            }

            true
        }
    }
}
