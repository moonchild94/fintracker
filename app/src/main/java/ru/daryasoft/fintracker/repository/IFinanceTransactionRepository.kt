package ru.daryasoft.fintracker.repository

import ru.daryasoft.fintracker.entity.FinanceTransaction

interface IFinanceTransactionRepository {
    fun getAll() : List<FinanceTransaction>
}