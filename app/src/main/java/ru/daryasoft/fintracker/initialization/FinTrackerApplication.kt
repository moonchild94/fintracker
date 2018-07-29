package ru.daryasoft.fintracker.initialization

import androidx.work.*
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import dagger.android.support.HasSupportFragmentInjector
import ru.daryasoft.fintracker.repository.RateWorker
import java.util.concurrent.TimeUnit

/**
 * Класс приложения.
 */
class FinTrackerApplication : DaggerApplication(), HasActivityInjector, HasSupportFragmentInjector {
    val mainComponent = DaggerMainComponent
            .builder()
            .create(this)
            .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return mainComponent

    }

    override fun onCreate() {
        super.onCreate()

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(RateWorker::class.java)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance().enqueue(oneTimeWorkRequest)

        val statusesByTag = WorkManager.getInstance().getStatusesByTag("rateWorker").value
        if (statusesByTag == null || statusesByTag.isEmpty() || statusesByTag[0] == null || statusesByTag[0].state.isFinished) {
            val periodicWorkRequest = PeriodicWorkRequest.Builder(RateWorker::class.java, 12, TimeUnit.HOURS, 1, TimeUnit.HOURS)
                    .setConstraints(constraints)
                    .addTag("rateWorker")
                    .build()
            WorkManager.getInstance().enqueue(periodicWorkRequest)
        }
    }
}
