package ru.daryasoft.fintracker.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import dagger.android.support.AndroidSupportInjection
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.calculator.IFinCalculator
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.repository.IFinTransactionRepository
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var finTransactionRepository: IFinTransactionRepository

    @Inject
    lateinit var finCalculator: IFinCalculator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)

        activity?.title = getString(R.string.title_fragment_main)
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val balanceView = rootView.findViewById<TextView>(R.id.balance)
        recalculateBalance(balanceView, Currency.RUB)

        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Currency.values().map { it.toString() })
        val spinner = rootView.findViewById<Spinner>(R.id.currencySpinner)
        spinner.adapter = adapter
        spinner.setSelection(Currency.RUB.ordinal)
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                recalculateBalance(balanceView, Currency.values()[position])
            }
        }

        return rootView
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
