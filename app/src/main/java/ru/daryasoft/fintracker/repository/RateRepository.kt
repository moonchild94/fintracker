package ru.daryasoft.fintracker.repository

import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.Rate
import java.util.*

/**
 * Репозиторий для работы с курсами валют.
 */
interface RateRepository {

    /**
     * Получить курс валюты относительно валюты по умолчанию.
     *
     * @param currency исходная валюта.
     *
     * @return курс валюты.
     */
    fun getRateToDefault(currency: Currency): Double

    /**
     * Получить курс валюты по умолчанию относительно переданной валюты.
     *
     * @param currency валюта, в которую происходит конвертация.
     *
     * @return курс валюты.
     */
    fun getRateFromDefault(currency: Currency): Double

    fun onRatesUpdate(newRates: List<Rate>)
}