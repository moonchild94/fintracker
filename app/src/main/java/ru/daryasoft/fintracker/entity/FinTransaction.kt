package ru.daryasoft.fintracker.entity

import java.util.*

data class FinTransaction(val currency: Currency, val sum: Double, val type: FinTransactionType, val date: Date)