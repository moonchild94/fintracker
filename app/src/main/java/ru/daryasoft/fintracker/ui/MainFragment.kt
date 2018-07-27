package ru.daryasoft.fintracker.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

import ru.daryasoft.fintracker.R

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
    }

    private fun initTabLayout() {
        val adapter = MainFragmentPagerAdapter(activity, childFragmentManager)
        view_pager.adapter = adapter

        sliding_tabs.setupWithViewPager(view_pager)
        sliding_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                activity?.title = when (tab?.position) {
                    0 -> getString(R.string.title_fragment_balance)
                    1 -> getString(R.string.title_fragment_operation)
                    else -> throw IllegalArgumentException()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
