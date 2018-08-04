package ru.daryasoft.fintracker.rate

import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


private const val WORK_MANAGER_TAG = "rateWorker"

class RateWorkerManager {

    fun onStart() {
        val constraints = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        //todo проверять, что ранее не загружены
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(RateWorker::class.java)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance()?.enqueue(oneTimeWorkRequest)

        val statusesByTag = WorkManager.getInstance()?.getStatusesByTag(WORK_MANAGER_TAG)?.value
        if (statusesByTag == null || statusesByTag.isEmpty() || statusesByTag[0] == null || statusesByTag[0].state.isFinished) {
            val periodicWorkRequest = PeriodicWorkRequest.Builder(RateWorker::class.java, 12, TimeUnit.HOURS, 1, TimeUnit.HOURS)
                    .setConstraints(constraints)
                    .addTag(WORK_MANAGER_TAG)
                    .build()
            WorkManager.getInstance()?.enqueue(periodicWorkRequest)
        }

    }


}