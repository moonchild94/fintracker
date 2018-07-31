package ru.daryasoft.fintracker.calculator

import ru.daryasoft.fintracker.entity.*
import ru.daryasoft.fintracker.transaction.TransactionRepository
import javax.inject.Inject

/**
 * Сервис для выполнения финансовых расчетов.
 */
class TransactionCalculationServiceImpl @Inject constructor(private val transactionRepository: TransactionRepository) : TransactionCalculationService {
    override fun aggregateByCategories(account: Account?, categories: List<Category>, targetCurrency: Currency): List<TransactionAggregateInfo> {
        val result = mutableListOf<TransactionAggregateInfo>()

        for (category in categories) {
            var sum = 0.00
            val transactions = transactionRepository.query(category, account).value
            for (transaction in transactions ?: listOf()) {
                sum += (if (transaction.category.transactionType == TransactionType.INCOME) transaction.sum else -transaction.sum)
            }
            result.add(TransactionAggregateInfo(targetCurrency, sum, category))
        }

        return result
    }
}