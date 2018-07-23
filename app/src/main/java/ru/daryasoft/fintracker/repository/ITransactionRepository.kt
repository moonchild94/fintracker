package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import ru.daryasoft.fintracker.entity.Transaction

/**
 * Репозиторий для работы с финансовыми транзакциями.
 */
interface ITransactionRepository {
    /**
     * Получить все финансовые транзакции.
     */
    fun getAll(): LiveData<List<Transaction>>
}