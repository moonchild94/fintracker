package ru.daryasoft.fintracker.repository

import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.FinTransaction
import ru.daryasoft.fintracker.entity.FinTransactionType
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FinTransactionRepository @Inject constructor() : IFinTransactionRepository {
    override fun getAll(): List<FinTransaction> =
            listOf(
                    FinTransaction(Currency.RUB, 10000.00, FinTransactionType.INCOME, Date()),
                    FinTransaction(Currency.RUB, 1000.00, FinTransactionType.OUTCOME, Date()),
                    FinTransaction(Currency.RUB, 1000.00, FinTransactionType.OUTCOME, Date()),

                    FinTransaction(Currency.USD, 1000.00, FinTransactionType.INCOME, Date()),
                    FinTransaction(Currency.USD, 1000.00, FinTransactionType.OUTCOME, Date()))
}