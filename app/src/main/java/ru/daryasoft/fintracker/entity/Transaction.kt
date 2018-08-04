package ru.daryasoft.fintracker.entity

import java.util.*

/**
 * Финансовая транзакция.
 */
data class Transaction(val account: Account, val sum: Money, val date: Date, val category: Category)