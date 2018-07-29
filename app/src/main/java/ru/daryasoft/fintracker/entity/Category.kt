package ru.daryasoft.fintracker.entity

/**
 * Категория доходов/расходов.
 */
data class Category (val name: String, val transactionType: TransactionType, val iconUri: String)