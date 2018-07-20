package ru.daryasoft.fintracker.initialization

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.daryasoft.fintracker.ui.MainActivity
import ru.daryasoft.fintracker.ui.MainFragment
import javax.inject.Singleton
import dagger.Binds
import ru.daryasoft.fintracker.calculator.FinCalculator
import ru.daryasoft.fintracker.calculator.IFinCalculator
import ru.daryasoft.fintracker.initialization.FinTrackerApplication
import ru.daryasoft.fintracker.repository.FinTransactionRepository
import ru.daryasoft.fintracker.repository.IFinTransactionRepository
import ru.daryasoft.fintracker.repository.IRateRepository
import ru.daryasoft.fintracker.repository.RateRepository

/**
 * Dagger-модуль с поставщиками зависимостей.
 */
@Module
interface MainBindModule {
    @ContributesAndroidInjector
    fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    fun contributeMainFragmentInjector(): MainFragment

    @Binds
    @Singleton
    fun bindFinCalculator(finCalculator: FinCalculator): IFinCalculator

    @Binds
    @Singleton
    fun bindFinTransactionRepository(finTransactionRepository: FinTransactionRepository): IFinTransactionRepository

    @Binds
    @Singleton
    fun bindRateRepository(rateRepository: RateRepository): IRateRepository
}
