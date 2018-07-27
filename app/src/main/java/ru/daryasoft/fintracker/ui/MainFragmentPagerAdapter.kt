package ru.daryasoft.fintracker.ui

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.ui.transaction.TransactionsFragment

/**
 *  Адаптер для переключения фрагментов на главной активити.
 */
class MainFragmentPagerAdapter(private val context: Context?, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BalanceFragment.newInstance()
            1 -> TransactionsFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context?.getString(R.string.title_fragment_balance) ?: throw IllegalArgumentException()
            1 -> context?.getString(R.string.title_fragment_operation) ?: throw IllegalArgumentException()
            else -> throw IllegalArgumentException()
        }
    }
}