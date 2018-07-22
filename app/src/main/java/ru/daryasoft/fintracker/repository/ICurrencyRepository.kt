package ru.daryasoft.fintracker.repository

import ru.daryasoft.fintracker.entity.Currency
import java.util.*

interface ICurrencyRepository {
    fun getDefaultCurrency() : Currency

    fun getRate(currencyFrom: Currency, currencyTo: Currency, date: Date): Double
}