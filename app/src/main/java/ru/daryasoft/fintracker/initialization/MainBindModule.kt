package ru.daryasoft.fintracker.initialization

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.daryasoft.fintracker.ui.MainActivity
import ru.daryasoft.fintracker.ui.MainFragment
import javax.inject.Singleton
import dagger.Binds
import dagger.multibindings.IntoMap
import ru.daryasoft.fintracker.calculator.FinCalculator
import ru.daryasoft.fintracker.calculator.IFinCalculator
import ru.daryasoft.fintracker.repository.TransactionRepositoryImpl
import ru.daryasoft.fintracker.repository.TransactionRepository
import ru.daryasoft.fintracker.repository.CurrencyRepository
import ru.daryasoft.fintracker.repository.CurrencyRepositoryImpl
import ru.daryasoft.fintracker.ui.TransactionsFragment
import ru.daryasoft.fintracker.viewmodel.BalanceViewModel
import ru.daryasoft.fintracker.viewmodel.TransactionsViewModel
import ru.daryasoft.fintracker.viewmodel.ViewModelFactory

/**
 * Dagger-модуль с поставщиками зависимостей.
 */
@Module
interface MainBindModule {
    @ContributesAndroidInjector
    fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    fun contributeMainFragmentInjector(): MainFragment

    @ContributesAndroidInjector
    fun contributeTransactionsFragmentInjector(): TransactionsFragment

    @Binds
    @Singleton
    fun bindFinCalculator(finCalculator: FinCalculator): IFinCalculator

    @Binds
    @Singleton
    fun bindTransactionRepository(TransactionRepository: TransactionRepositoryImpl): TransactionRepository

    @Binds
    @Singleton
    fun bindRateRepository(rateRepository: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BalanceViewModel::class)
    fun postBalanceViewModel(viewModel: BalanceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsViewModel::class)
    fun postTransactionsViewModel(viewModel: TransactionsViewModel): ViewModel
}
