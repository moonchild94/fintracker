package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.entity.TransactionType
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с финансовыми транзакциями.
 */
@Singleton
class TransactionRepository @Inject constructor() : ITransactionRepository {
    private val transactions = MutableLiveData<List<Transaction>>()

    override fun getAll(): LiveData<List<Transaction>> {
        transactions.value = listOf(
                Transaction(Currency.RUB, 10000.00, TransactionType.INCOME, Date()),
                Transaction(Currency.RUB, 1000.00, TransactionType.OUTCOME, Date()),
                Transaction(Currency.RUB, 1000.00, TransactionType.OUTCOME, Date()),
                Transaction(Currency.USD, 1000.00, TransactionType.INCOME, Date()),
                Transaction(Currency.USD, 1000.00, TransactionType.OUTCOME, Date()))
        return transactions
    }
}