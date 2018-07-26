package ru.daryasoft.fintracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.calculator.IFinCalculator
import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.Constants
import ru.daryasoft.fintracker.repository.CurrencyRepository
import ru.daryasoft.fintracker.repository.TransactionRepository
import javax.inject.Inject

/**
 * ViewModel для баланса.
 */
class BalanceViewModel @Inject constructor(currencyRepository: CurrencyRepository,
                                           private val transactionRepository: TransactionRepository,
                                           private val finCalculator: IFinCalculator) : ViewModel() {

    private val balance = MutableLiveData<Balance>()

    init {
        currencyRepository.getDefaultCurrency().observeForever {
            recalculateBalance(it ?: Constants.DEFAULT_CURRENCY)
        }
    }

    fun getBalance(): LiveData<Balance> {
        return balance
    }

    fun setCurrentCurrency(currency: Currency) {
        recalculateBalance(currency)
    }

    private fun recalculateBalance(currency: Currency) {
        val transactions = transactionRepository.getAll()
        balance.value = finCalculator.sum(transactions.value ?: listOf(), currency)
    }
}