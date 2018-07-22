package ru.daryasoft.fintracker.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.addSingleFragment(fragment: Fragment, id: Int) {
    supportFragmentManager.beginTransaction()
            .replace(id, fragment)
            .commit()
}

inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]
