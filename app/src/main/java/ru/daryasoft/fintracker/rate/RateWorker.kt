package ru.daryasoft.fintracker.rate

import androidx.work.Worker
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ru.daryasoft.fintracker.initialization.FinTrackerApplication
import javax.inject.Inject

/**
 * Задача для обновления курса валют из сети.
 */
class RateWorker : Worker() {

    @Inject
    lateinit var rateRepository: RateRepository

    @Inject
    lateinit var rateNetworkDataSource: RateNetworkDataSource

    override fun doWork(): Result {
        if (applicationContext is FinTrackerApplication) {
            (applicationContext as FinTrackerApplication).mainComponent.injectRateWorker(this)
        }

        val rates = rateNetworkDataSource.getRates().execute().body() ?: listOf()
        launch(UI) { rateRepository.onRatesUpdate(rates) }

        return Result.SUCCESS
    }
}