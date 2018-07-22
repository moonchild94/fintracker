package ru.daryasoft.fintracker.calculator

import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.entity.TransactionType
import ru.daryasoft.fintracker.repository.ICurrencyRepository
import javax.inject.Inject

/**
 * Сервис для выполнения финансовых расчетов.
 */
class FinCalculator @Inject constructor(var currencyRepository: ICurrencyRepository): IFinCalculator {
    override fun sum(transactions: List<Transaction>, targetCurrency: Currency): Balance {
        var sum = 0.00

        for (transaction in transactions) {
            val rate = currencyRepository.getRate(transaction.currency, targetCurrency, transaction.date)
            val sumWithRate = transaction.sum * rate
            sum += (if (transaction.type == TransactionType.INCOME) sumWithRate else -sumWithRate)
        }

        return Balance(targetCurrency, sum)
    }
}