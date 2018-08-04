package ru.daryasoft.fintracker.account

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.Money
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы со счетами.
 */
@Singleton
class AccountRepositoryImpl @Inject constructor() : AccountRepository {

    private val accounts = MutableLiveData<List<Account>>()

    init {
        accounts.value = mutableListOf(
                Account("Наличные руб", Money(BigDecimal.valueOf(600.00), Currency.RUB)),
                Account("Карта руб", Money(BigDecimal.valueOf(1600.00), Currency.RUB)),
                Account("Карта доллары", Money(BigDecimal.valueOf(20600.00), Currency.USD)))
    }

    override fun getAll(): LiveData<List<Account>> {
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