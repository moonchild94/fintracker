package ru.daryasoft.fintracker.entity

/**
 * Агрегированная информация по множеству транзакций.
 */
data class TransactionAggregateInfo(val currency: Currency, val amount: Double, val category: Category)