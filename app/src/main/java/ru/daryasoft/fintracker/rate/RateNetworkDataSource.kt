package ru.daryasoft.fintracker.rate

import retrofit2.Call
import retrofit2.http.GET
import ru.daryasoft.fintracker.entity.Rate


/**
 * Сервис для обращения к API для получения курсов валют относительно валюты по умолчанию.
 */
interface RateNetworkDataSource {

    /**
     * Получает список курсов валют относительно валюты по умолчанию.
     */
    @GET("daily_json.js")
    fun getRates(): Call<List<Rate>>
}