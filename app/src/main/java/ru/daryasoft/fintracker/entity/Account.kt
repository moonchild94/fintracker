package ru.daryasoft.fintracker.entity

/**
 * Счет.
 */
data class Account(val name: String, var amount: Double, val currency: Currency)