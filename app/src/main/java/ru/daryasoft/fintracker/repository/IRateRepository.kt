package ru.daryasoft.fintracker.repository

import ru.daryasoft.fintracker.entity.Currency
import java.util.*

interface IRateRepository {
    fun getRate(currencyFrom: Currency, currencyTo: Currency, date: Date): Double
}