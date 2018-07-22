package ru.daryasoft.fintracker.calculator

import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.Transaction

interface IFinCalculator {
    fun sum(transactions: List<Transaction>, targetCurrency: Currency) : Balance
}