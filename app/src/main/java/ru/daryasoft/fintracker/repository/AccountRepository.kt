package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import ru.daryasoft.fintracker.entity.Account

/**
 * Репозиторий для работы со счетами.
 */
interface AccountRepository {
    fun getAll(): LiveData<List<Account>>

    fun delete(account: Account)

    fun add(account: Account)
}