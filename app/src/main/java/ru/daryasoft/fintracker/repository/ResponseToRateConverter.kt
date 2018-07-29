package ru.daryasoft.fintracker.repository

import android.util.Log
import com.github.mikephil.charting.charts.Chart.LOG_TAG
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Converter
import ru.daryasoft.fintracker.Constants
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.Rate
import java.util.*

/**
 * Конвертер для получения списка сущностей с информацией о курсах валют из ответа сервиса.
 */

private const val CANT_CONVERT_ERROR_MESSAGE = "Can't convert service response into list of rate entities."

class ResponseToRateConverter : Converter<ResponseBody, List<Rate>> {

    override fun convert(responseBody: ResponseBody): List<Rate> {
        val rateList = mutableListOf<Rate>()
        try {
            val date = Date()
            val items = JSONObject(responseBody.string()).getJSONObject("Valute")
            for (currency in Currency.values()) {
                if (currency != Constants.DEFAULT_CURRENCY) {
                    val item = items.getJSONObject(currency.name)
                    rateList.add(Rate(currency, item.getDouble("Value"), date))
                }
            }
        } catch (e: JSONException) {
            Log.e(LOG_TAG, CANT_CONVERT_ERROR_MESSAGE, e)
        }

        return rateList
    }
}
