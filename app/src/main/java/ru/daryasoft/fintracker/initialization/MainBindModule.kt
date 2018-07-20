package ru.daryasoft.yadiskviewer.initialization

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.daryasoft.fintracker.ui.MainActivity
import ru.daryasoft.fintracker.ui.MainFragment
import javax.inject.Singleton
import dagger.Binds
import ru.daryasoft.fintracker.repository.FinanceTransactionRepository
import ru.daryasoft.fintracker.repository.IFinanceTransactionRepository


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
    fun provideFinanceTransactionRepository(financeTransactionRepository: FinanceTransactionRepository): IFinanceTransactionRepository
}
