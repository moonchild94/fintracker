package ru.daryasoft.fintracker.calculator

import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.FinTransaction
import ru.daryasoft.fintracker.entity.FinTransactionType
import ru.daryasoft.fintracker.repository.ICurrencyRepository
import javax.inject.Inject

class FinCalculator @Inject constructor(var currencyRepository: ICurrencyRepository): IFinCalculator {
    override fun sum(finTransactions: List<FinTransaction>, targetCurrency: Currency): Balance {
        var sum = 0.00

        for (finTransaction in finTransactions) {
            val rate = currencyRepository.getRate(finTransaction.currency, targetCurrency, finTransaction.date)
            val sumWithRate = finTransaction.sum * rate
            sum += (if (finTransaction.type == FinTransactionType.INCOME) sumWithRate else -sumWithRate)
        }

        return Balance(targetCurrency, sum)
    }
}