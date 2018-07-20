package ru.daryasoft.fintracker.repository

import ru.daryasoft.fintracker.entity.FinanceTransaction
import ru.daryasoft.fintracker.entity.TransactionType
import java.util.*

class FinanceTransactionRepository : IFinanceTransactionRepository {
    override fun getAll(): List<FinanceTransaction> =
            listOf(
                    FinanceTransaction(Currency.getInstance(Locale("RU")), 10000.00, TransactionType.INCOME),
                    FinanceTransaction(Currency.getInstance(Locale("RU")), 1000.00, TransactionType.OUTCOME),
                    FinanceTransaction(Currency.getInstance(Locale("RU")), 1000.00, TransactionType.OUTCOME),

                    FinanceTransaction(Currency.getInstance(Locale("US")), 1000.00, TransactionType.INCOME),
                    FinanceTransaction(Currency.getInstance(Locale("US")), 1000.00, TransactionType.OUTCOME))
}