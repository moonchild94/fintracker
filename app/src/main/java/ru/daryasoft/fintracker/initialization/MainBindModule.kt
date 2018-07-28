package ru.daryasoft.fintracker.initialization

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.daryasoft.fintracker.ui.MainActivity
import ru.daryasoft.fintracker.ui.BalanceFragment
import javax.inject.Singleton
import dagger.Binds
import dagger.multibindings.IntoMap
import ru.daryasoft.fintracker.calculator.FinCalculator
import ru.daryasoft.fintracker.calculator.IFinCalculator
import ru.daryasoft.fintracker.repository.*
import ru.daryasoft.fintracker.ui.account.AccountsFragment
import ru.daryasoft.fintracker.ui.transaction.AddTransactionFragment
import ru.daryasoft.fintracker.ui.transaction.TransactionsFragment
import ru.daryasoft.fintracker.viewmodel.*

/**
 * Dagger-модуль с поставщиками зависимостей.
 */
@Module
interface MainBindModule {
    @ContributesAndroidInjector
    fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    fun contributeMainFragmentInjector(): BalanceFragment

    @ContributesAndroidInjector
    fun contributeTransactionsFragmentInjector(): TransactionsFragment

    @ContributesAndroidInjector
    fun contributeAccountsFragmentInjector(): AccountsFragment

    @ContributesAndroidInjector
    fun contributeAddTransactionFragmentInjector(): AddTransactionFragment

    @Binds
    @Singleton
    fun bindFinCalculator(finCalculator: FinCalculator): IFinCalculator

    @Binds
    @Singleton
    fun bindTransactionRepository(transactionRepository: TransactionRepositoryImpl): TransactionRepository

    @Binds
    @Singleton
    fun bindAccountRepository(accountRepository: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    fun bindRateRepository(rateRepository: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    @Singleton
    fun bindCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository

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

    @Binds
    @IntoMap
    @ViewModelKey(AccountsViewModel::class)
    fun postAccountsViewModel(viewModel: AccountsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    fun postCategoriesViewModel(viewModel: CategoriesViewModel): ViewModel
}
