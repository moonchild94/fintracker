package ru.daryasoft.fintracker.balance

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.account.AccountRepository
import ru.daryasoft.fintracker.calculator.TransactionCalculationService
import ru.daryasoft.fintracker.category.CategoryRepository
import ru.daryasoft.fintracker.common.Constants
import ru.daryasoft.fintracker.entity.*
import ru.daryasoft.fintracker.rate.RateRepository
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
        val liveData = MutableLiveData<Account>()
        liveData.value = accounts.value?.get(0)
        liveData
    }

    val currency by lazy {
        val liveData = MutableLiveData<Currency>()
        liveData.value = account.value?.currency ?: Constants.DEFAULT_CURRENCY
        liveData
    }

    val balance = MutableLiveData<Balance>()

    val transactionAggregateInfoList by lazy {
        val liveData = MutableLiveData<List<TransactionAggregateInfo>>()
        liveData.value = transactionCalculationService.aggregateByCategories(account.value,
                categoryRepository.findByTransactionType(TransactionType.OUTCOME).value
                        ?: listOf(), Constants.DEFAULT_CURRENCY)
        liveData
    }

    fun onChangeAccount(newAccount: Account) {
        account.value = newAccount
        onChangeCurrency(account.value?.currency ?: Constants.DEFAULT_CURRENCY)
    }

    fun onChangeCurrency(newCurrency: Currency) {
        currency.value = newCurrency
        recalculateBalance()
    }

    private fun recalculateBalance() {
        val currencyValue = currency.value ?: Constants.DEFAULT_CURRENCY
        val rateToDefault = rateRepository.getRateToDefault(account.value?.currency
                ?: Constants.DEFAULT_CURRENCY)
        val rateFromDefault = rateRepository.getRateFromDefault(currencyValue)
        balance.value = Balance(currencyValue, (account.value?.amount
                ?: 0.0) * rateToDefault * rateFromDefault)
        transactionAggregateInfoList.value = transactionCalculationService.aggregateByCategories(account.value,
                categoryRepository.findByTransactionType(TransactionType.OUTCOME).value
                        ?: listOf(), currencyValue)
    }
}