package ru.daryasoft.fintracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.repository.ITransactionRepository
import javax.inject.Inject

/**
 * ViewModel для списка транзакций.
 */
class TransactionsViewModel @Inject constructor(private val transactionRepository: ITransactionRepository) : ViewModel() {
    private lateinit var transactions: LiveData<List<Transaction>>

    fun getTransactions(): LiveData<List<Transaction>> {
        if (!::transactions.isInitialized) {
            transactions = transactionRepository.getAll()
        }
        return transactions
    }
}