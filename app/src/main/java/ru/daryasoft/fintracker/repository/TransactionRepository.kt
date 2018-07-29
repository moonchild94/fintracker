package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.entity.Category
import ru.daryasoft.fintracker.entity.Transaction

/**
 * Репозиторий для работы с финансовыми транзакциями.
 */
interface TransactionRepository {
    /**
     * Получить все финансовые транзакции.
     */
    fun getAll(): LiveData<List<Transaction>>

    fun query(category: Category, account: Account?): LiveData<List<Transaction>>

    fun add(transaction: Transaction)

    fun delete(transaction: Transaction)
}