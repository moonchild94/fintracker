package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.entity.Currency
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы со счетами.
 */
@Singleton
class AccountRepositoryImpl @Inject constructor() : AccountRepository {

    private val accounts = MutableLiveData<List<Account>>()

    override fun getAll(): LiveData<List<Account>> {
        accounts.value = mutableListOf(
                Account("Наличные руб", 600.00, Currency.RUB),
                Account("Карта руб", 1600.00, Currency.RUB),
                Account("Карта доллары", 20600.00, Currency.USD))
        return accounts
    }

    override fun delete(account: Account) {
        val mutableList = accounts.value?.toMutableList()
        mutableList?.remove(account)
        accounts.value = mutableList
    }

    override fun add(account: Account) {
        val mutableList = accounts.value?.toMutableList()
        mutableList?.add(account)
        accounts.value = mutableList
    }
}