package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.FinTransaction
import ru.daryasoft.fintracker.entity.FinTransactionType
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FinTransactionRepository @Inject constructor() : IFinTransactionRepository {
    override fun getAll(): LiveData<List<FinTransaction>> {
        val transactions = MutableLiveData<List<FinTransaction>>()
        transactions.value = listOf(
                FinTransaction(Currency.RUB, 10000.00, FinTransactionType.INCOME, Date()),
                FinTransaction(Currency.RUB, 1000.00, FinTransactionType.OUTCOME, Date()),
                FinTransaction(Currency.RUB, 1000.00, FinTransactionType.OUTCOME, Date()),
                FinTransaction(Currency.USD, 1000.00, FinTransactionType.INCOME, Date()),
                FinTransaction(Currency.USD, 1000.00, FinTransactionType.OUTCOME, Date()))
        return transactions
    }
}