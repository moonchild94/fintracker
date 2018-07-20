package ru.daryasoft.fintracker.calculator

import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.FinTransaction
import ru.daryasoft.fintracker.entity.FinTransactionType
import ru.daryasoft.fintracker.repository.IRateRepository
import javax.inject.Inject

class FinCalculator @Inject constructor(var rateRepository: IRateRepository): IFinCalculator {
    override fun sum(finTransactions: List<FinTransaction>, targetCurrency: Currency): Double {
        var sum = 0.00

        for (finTransaction in finTransactions) {
            val rate = rateRepository.getRate(finTransaction.currency, targetCurrency, finTransaction.date)
            val sumWithRate = finTransaction.sum * rate
            sum += (if (finTransaction.type == FinTransactionType.INCOME) sumWithRate else -sumWithRate)
        }

        return sum
    }
}