package ru.daryasoft.fintracker.repository

import android.app.Application
import android.content.SharedPreferences
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.main.Constants
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с валютами.
 */
@Singleton
class CurrencyRepository @Inject constructor(private val sharedPreferences: SharedPreferences, private val context: Application)
    : ICurrencyRepository {
    override fun getDefaultCurrency() : Currency {
        val defaultCurrencyPreferenceKey = context.getString(R.string.currency_list_preference_key)
        return Currency.valueOf(sharedPreferences.getString(defaultCurrencyPreferenceKey, Constants.DEFAULT_CURRENCY.toString()))
    }

    override fun getRate(currencyFrom: Currency, currencyTo: Currency, date: Date): Double {
        if (currencyFrom == currencyTo) {
            return 1.00
        }
        return when (currencyFrom) {
            Currency.RUB -> 0.02
            Currency.USD -> 50.00
        }
    }
}