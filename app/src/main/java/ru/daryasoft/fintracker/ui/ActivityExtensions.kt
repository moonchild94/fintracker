package ru.daryasoft.fintracker.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.changeFragment(fragment: Fragment, id: Int) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(id, fragment)
    transaction.commit()
}
