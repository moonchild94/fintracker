package ru.daryasoft.fintracker.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.repository.IFinanceTransactionRepository
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var financeTransactionRepository: IFinanceTransactionRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_fragment_main)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
