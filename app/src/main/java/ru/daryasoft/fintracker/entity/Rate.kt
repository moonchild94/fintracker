package ru.daryasoft.fintracker.entity

import java.util.*

/**
 * Курс валюты (по отношению к валюте по умолчанию).
 */
data class Rate(val currency: Currency, val ratio: Double, val date: Date)