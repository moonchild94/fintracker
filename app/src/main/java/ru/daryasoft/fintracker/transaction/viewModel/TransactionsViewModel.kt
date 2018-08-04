package ru.daryasoft.fintracker.transaction.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.common.SingleLiveEvent
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.transaction.data.TransactionRepository
import javax.inject.Inject

/**
 * ViewModel для списка транзакций.
 */
class TransactionsViewModel @Inject constructor(private val transactionRepository: TransactionRepository) : ViewModel() {

    private val _showDialogDelete = SingleLiveEvent<Any>()
    private var positionDelete: Int = -1
    private var _positionCancelDelete: MutableLiveData<Int> = MutableLiveData<Int>()

    val positionCancelDelete: LiveData<Int>
        get() = _positionCancelDelete

    val showDialogDelete: LiveData<Any>
        get() = _showDialogDelete

    val transactions: LiveData<List<Transaction>> by lazy {
        transactionRepository.getAll()
    }

    fun onDeleteTransaction(position: Int) {
        positionDelete = position
        _showDialogDelete.call()
    }

    fun onConfirmDeleteTransaction() {
        if (positionDelete != -1) {
            transactions.value?.get(positionDelete)?.let { transactionRepository.delete(it) }
            positionDelete = -1
        }
    }

    fun onCancelDeleteTransaction() {
        if (positionDelete != -1) {
            _positionCancelDelete.value = positionDelete
            positionDelete = -1
        }
    }


    fun onAddTransaction(transaction: Transaction) {
        transactionRepository.add(transaction)
    }
}