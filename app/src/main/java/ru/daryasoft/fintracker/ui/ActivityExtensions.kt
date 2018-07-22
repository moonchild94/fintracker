package ru.daryasoft.fintracker.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.addSingleFragment(fragment: Fragment, id: Int) {
    supportFragmentManager.beginTransaction()
            .replace(id, fragment)
            .commit()
}
