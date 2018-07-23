package ru.daryasoft.fintracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.calculator.IFinCalculator
import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.main.Constants
import ru.daryasoft.fintracker.repository.CurrencyRepository
import ru.daryasoft.fintracker.repository.TransactionRepository
import javax.inject.Inject

/**
 * ViewModel для баланса.
 */
class BalanceViewModel @Inject constructor(private val transactionRepository: TransactionRepository,
                                           private val currencyRepository: CurrencyRepository,
                                           private val finCalculator: IFinCalculator) : ViewModel() {

    private lateinit var balance: MutableLiveData<Balance>
    private lateinit var defaultCurrency: LiveData<Currency>

    init {
        getDefaultCurrency().observeForever { setCurrentCurrency(it ?: Constants.DEFAULT_CURRENCY) }
    }

    fun getBalance(): LiveData<Balance> {
        return balance
    }

    fun setCurrentCurrency(currency: Currency) {
        if (!::balance.isInitialized) {
            balance = MutableLiveData()
        }
        val transactions = transactionRepository.getAll()
        balance.value = finCalculator.sum(transactions.value ?: listOf(), currency)
    }

    private fun getDefaultCurrency(): LiveData<Currency> {
        if (!::defaultCurrency.isInitialized) {
            defaultCurrency = MutableLiveData()
            defaultCurrency = currencyRepository.getDefaultCurrency()
        }
        return defaultCurrency
    }
}