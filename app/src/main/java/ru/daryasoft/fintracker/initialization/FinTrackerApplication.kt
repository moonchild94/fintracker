package ru.daryasoft.fintracker.initialization

import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import dagger.android.support.HasSupportFragmentInjector

/**
 * Класс приложения.
 */
class FinTrackerApplication : DaggerApplication(), HasActivityInjector, HasSupportFragmentInjector {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMainComponent
                .builder()
                .create(this)
                .build()
    }
}
