package ru.daryasoft.fintracker.entity

import java.util.*

data class FinanceTransaction(val currency: Currency, val sum : Double, val transactionType : TransactionType) {
}