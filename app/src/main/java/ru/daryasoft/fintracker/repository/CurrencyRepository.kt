package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import ru.daryasoft.fintracker.entity.Currency
import java.util.*

/**
 * Репозиторий для работы с валютами.
 */
interface CurrencyRepository {
    /**
     * Получить основную валюту.
     *
     * @return основная валюта.
     */
    fun getDefaultCurrency(): LiveData<Currency>

    /**
     * Получить курс одной валюты относительно другой.
     *
     * @param currencyFrom исходная валюта.
     * @param currencyTo итоговая валюта.
     *
     * @return курс валюты.
     */
    fun getRate(currencyFrom: Currency, currencyTo: Currency, date: Date): Double
}