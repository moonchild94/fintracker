package ru.daryasoft.fintracker.repository

import ru.daryasoft.fintracker.entity.Currency
import java.util.*

/**
 * Репозиторий для работы с валютами.
 */
interface ICurrencyRepository {
    /**
     * Получить основную валюту.
     *
     * @return основная валюта.
     */
    fun getDefaultCurrency(): Currency

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