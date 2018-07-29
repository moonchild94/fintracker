package ru.daryasoft.fintracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.calculator.TransactionCalculationService
import ru.daryasoft.fintracker.entity.*
import ru.daryasoft.fintracker.repository.AccountRepository
import ru.daryasoft.fintracker.repository.CategoryRepository
import ru.daryasoft.fintracker.repository.RateRepository
import javax.inject.Inject

/**
 * ViewModel для баланса.
 */
class BalanceViewModel @Inject constructor(private val accountRepository: AccountRepository,
                                           private val categoryRepository: CategoryRepository,
                                           private val rateRepository: RateRepository,
                                           private val transactionCalculationService: TransactionCalculationService) : ViewModel() {

    val accounts by lazy {
        accountRepository.getAll()
    }

    val account by lazy {
        val data = MutableLiveData<Account>()
        data.value = accountRepository.getAll().value?.get(0)
        data
    }

    val currency by lazy {
        val data = MutableLiveData<Currency>()
        data.value = account.value?.currency ?: Currency.RUB
        data
    }

    val balance = MutableLiveData<Balance>()

    val transactionAggregateInfoList by lazy {
        val data = MutableLiveData<List<TransactionAggregateInfo>>()
        data.value = transactionCalculationService.aggregateByCategories(account.value,
                categoryRepository.findByTransactionType(TransactionType.OUTCOME).value
                        ?: listOf(), Currency.RUB)
        data
    }

    fun onChangeAccount(newAccount: Account) {
        account.value = newAccount
        onChangeCurrency(account.value?.currency ?: Currency.RUB)
    }

    fun onChangeCurrency(newCurrency: Currency) {
        val rateToDefault = rateRepository.getRateToDefault(account.value?.currency ?: Currency.RUB)
        val rateFromDefault = rateRepository.getRateFromDefault(newCurrency)
        currency.value = newCurrency
        balance.value = Balance(newCurrency, (account.value?.amount
                ?: 0.0) * rateToDefault * rateFromDefault)
        transactionAggregateInfoList.value = transactionCalculationService.aggregateByCategories(account.value,
                categoryRepository.findByTransactionType(TransactionType.OUTCOME).value
                        ?: listOf(), newCurrency)
    }
}