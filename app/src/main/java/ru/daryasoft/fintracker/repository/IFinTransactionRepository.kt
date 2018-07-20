package ru.daryasoft.fintracker.repository

import ru.daryasoft.fintracker.entity.FinTransaction

interface IFinTransactionRepository {
    fun getAll() : List<FinTransaction>
}