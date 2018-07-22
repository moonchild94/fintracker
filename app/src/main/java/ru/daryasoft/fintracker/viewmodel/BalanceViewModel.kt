package ru.daryasoft.fintracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.calculator.IFinCalculator
import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.repository.ICurrencyRepository
import ru.daryasoft.fintracker.repository.ITransactionRepository
import javax.inject.Inject

/**
 * ViewModel для баланса.
 */
class BalanceViewModel @Inject constructor(private val transactionRepository: ITransactionRepository,
                                           private val currencyRepository: ICurrencyRepository,
                                           private val finCalculator: IFinCalculator) : ViewModel() {
    private lateinit var balance: MutableLiveData<Balance>

    fun getBalance(): LiveData<Balance> {
        if (!::balance.isInitialized) {
            balance = MutableLiveData()
            balance = calculateBalance(currencyRepository.getDefaultCurrency())
        }
        return balance
    }

    fun onDefaultCurrencyChanged() {
        balance = calculateBalance(currencyRepository.getDefaultCurrency())
    }

    fun setCurrentCurrency(currency: Currency) {
        balance = calculateBalance(currency)
    }

    private fun calculateBalance(currency: Currency): MutableLiveData<Balance> {
        val transactions = transactionRepository.getAll()
        balance.value = finCalculator.sum(transactions.value ?: listOf(), currency)
        return balance
    }
}