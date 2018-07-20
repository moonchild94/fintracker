package ru.daryasoft.fintracker.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.calculator.IFinCalculator
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.repository.IFinTransactionRepository
import javax.inject.Inject

class MainFragment : DaggerFragment() {
    @Inject
    lateinit var finTransactionRepository: IFinTransactionRepository

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var finCalculator: IFinCalculator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = getString(R.string.title_fragment_main)

        val defaultCurrency = Currency.valueOf(sharedPreferences.getString(getString(R.string.currency_list_preference_key), Currency.RUB.toString()))
        recalculateBalance(balance, defaultCurrency)

        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Currency.values().map { it.toString() })
        currencySpinner.adapter = adapter
        currencySpinner.setSelection(Currency.RUB.ordinal)
        currencySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                recalculateBalance(balance, Currency.values()[position])
            }
        }
    }

    private fun recalculateBalance(balanceView: TextView, currency: Currency) {
        val balance = finCalculator.sum(finTransactionRepository.getAll(), currency)
        balanceView.text = "$balance"
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
