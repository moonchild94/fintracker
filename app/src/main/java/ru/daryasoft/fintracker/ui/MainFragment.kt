package ru.daryasoft.fintracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
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

/**
 * Главный фрагмент (содержащий баланс пользователя).
 */
class MainFragment : DaggerFragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var balanceViewModel: BalanceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        balanceViewModel = getViewModel(viewModelFactory)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currencySpinner.adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Currency.values().map { it.toString() })
        currencySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                balanceViewModel.setCurrentCurrency(Currency.values()[position])
            }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(this)

        balanceViewModel.getBalance()
                .observe(this@MainFragment, Observer<Balance> {
                    balance.text = it?.sum.toString()
                    currencySpinner.setSelection(it?.currency?.ordinal ?: Currency.RUB.ordinal)
                })
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == getString(R.string.currency_list_preference_key)) {
            balanceViewModel.onDefaultCurrencyChanged()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
