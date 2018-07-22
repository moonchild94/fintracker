package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import ru.daryasoft.fintracker.entity.FinTransaction

interface IFinTransactionRepository {
    fun getAll() : LiveData<List<FinTransaction>>
}