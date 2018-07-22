package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import ru.daryasoft.fintracker.entity.Transaction

interface ITransactionRepository {
    fun getAll() : LiveData<List<Transaction>>
}