package ru.daryasoft.fintracker.initialization

import android.app.Application
import android.content.SharedPreferences
import android.support.v7.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger-модуль с поставщиками зависимостей.
 */
@Module
class MainProvideModule {

    @Provides
    @Singleton
    fun provideSharedPreference(context: Application) : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
}