package ru.daryasoft.fintracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_balance.*
import ru.daryasoft.fintracker.Constants
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.viewmodel.BalanceViewModel
import javax.inject.Inject

/**
 * Главный фрагмент (содержащий баланс пользователя).
 */
class BalanceFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val balanceViewModel: BalanceViewModel by lazy { getViewModel<BalanceViewModel>(viewModelFactory) }
    private val observer: Observer<Balance> by lazy {
        Observer<Balance> {
            balance.text = it?.sum.toString()
            currency_spinner.setSelection(it?.currency?.ordinal
                    ?: Constants.DEFAULT_CURRENCY.ordinal)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSpinner()
    }

    override fun onStart() {
        super.onStart()
        balanceViewModel.getBalance().observe(this@BalanceFragment, observer)
    }

    override fun onStop() {
        super.onStop()
        balanceViewModel.getBalance().removeObserver(observer)
    }

    private fun initSpinner() {
        currency_spinner.adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Currency.values().map { it.toString() })
        currency_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                balanceViewModel.setCurrentCurrency(Currency.values()[position])
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BalanceFragment()
    }
}
