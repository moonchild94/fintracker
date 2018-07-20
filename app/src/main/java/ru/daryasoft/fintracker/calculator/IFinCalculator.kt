package ru.daryasoft.fintracker.calculator

import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.FinTransaction

interface IFinCalculator {
    fun sum(finTransactions: List<FinTransaction>, targetCurrency: Currency) : Double
}