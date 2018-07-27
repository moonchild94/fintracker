package ru.daryasoft.fintracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.repository.TransactionRepository
import javax.inject.Inject

/**
 * ViewModel для списка транзакций.
 */
class TransactionsViewModel @Inject constructor(private val transactionRepository: TransactionRepository) : ViewModel() {

    val transactions: LiveData<List<Transaction>> by lazy {
        transactionRepository.getAll()
    }

    fun onDeleteTransaction(position: Int) {
        transactions.value?.get(position)?.let { transactionRepository.delete(it) }
    }

    fun onAddTransaction(transaction: Transaction) {
        transactionRepository.add(transaction)
    }
}