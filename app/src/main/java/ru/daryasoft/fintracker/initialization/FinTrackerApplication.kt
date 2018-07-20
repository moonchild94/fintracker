package ru.daryasoft.fintracker.initialization

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Класс приложения.
 */
class FinTrackerApplication : DaggerApplication(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = fragmentInjector

    override fun applicationInjector(): AndroidInjector<FinTrackerApplication> {
        return DaggerMainComponent
                .builder()
                .create(this)
                .build()
    }
}
