package ru.daryasoft.fintracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.viewmodel.BalanceViewModel
import ru.daryasoft.fintracker.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = getString(R.string.title_fragment_main)

        val balanceViewModel: BalanceViewModel = getViewModel(viewModelFactory)

        currencySpinner.adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Currency.values().map { it.toString() })
        currencySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                balanceViewModel.setCurrentCurrency(Currency.values()[position])
            }
        }

        balanceViewModel.getBalance()
                .observe(this@MainFragment, Observer<Balance> {
                    balance.text = it?.sum.toString()
                    currencySpinner.setSelection(it?.currency?.ordinal ?: Currency.RUB.ordinal)
                })
    }

    private inline fun <reified T : ViewModel> getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
            ViewModelProviders.of(this, viewModelFactory)[T::class.java]

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
