package ru.daryasoft.fintracker.transaction.data

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

    /**
     * Ищет транзакции по категории и счету.
     *
     * @category категория, по которой нужно выполнить поиск.
     * @account счет, по которому нужно выполнить поиск.
     */
    fun query(category: Category, account: Account?): LiveData<List<Transaction>>

    /**
     * Добавляет транзакцию.
     *
     * @transaction транзакция для добавления.
     */
    fun add(transaction: Transaction)

    /***
     * Удаляет транзакцию.
     *
     * @transaction транзакция для удаления.
     */
    fun delete(transaction: Transaction)
}