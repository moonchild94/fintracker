package ru.daryasoft.fintracker.ui

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.daryasoft.fintracker.R
import java.lang.IllegalArgumentException

class MainFragmentPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment.newInstance()
            1 -> OperationFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.title_fragment_main)
            1 -> context.getString(R.string.title_fragment_operation)
            else -> throw IllegalArgumentException()
        }
    }
}