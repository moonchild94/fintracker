package ru.daryasoft.fintracker.entity

import java.util.*

/**
 * Финансовая транзакция.
 */
data class Transaction(val currency: Currency, val sum: Double, val type: TransactionType, val date: Date)