package ru.daryasoft.fintracker.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
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
class CurrencyRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences, private val context: Application)
    : CurrencyRepository, SharedPreferences.OnSharedPreferenceChangeListener {

    private val currency = MutableLiveData<Currency>()

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun getDefaultCurrency(): LiveData<Currency> {
        val defaultCurrencyPreferenceKey = context.getString(R.string.currency_list_preference_key)
        currency.value = Currency.valueOf(sharedPreferences.getString(defaultCurrencyPreferenceKey, Constants.DEFAULT_CURRENCY.toString()))
        return currency
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

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == context.getString(R.string.currency_list_preference_key)) {
            getDefaultCurrency()
        }
    }
}