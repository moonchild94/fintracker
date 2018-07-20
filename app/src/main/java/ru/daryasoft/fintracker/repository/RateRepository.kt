package ru.daryasoft.fintracker.repository

import ru.daryasoft.fintracker.entity.Currency
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RateRepository @Inject constructor(): IRateRepository {
    override fun getRate(currencyFrom: Currency, currencyTo: Currency, date: Date) : Double {
        if (currencyFrom == currencyTo) {
            return 1.00
        }
        return when(currencyFrom) {
            Currency.RUB -> 0.02
            Currency.USD -> 50.00
        }
    }
}