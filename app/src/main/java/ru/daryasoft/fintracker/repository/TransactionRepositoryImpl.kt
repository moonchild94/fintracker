package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.daryasoft.fintracker.entity.*
import ru.daryasoft.fintracker.entity.Currency
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с финансовыми транзакциями.
 */
@Singleton
class TransactionRepositoryImpl @Inject constructor() : TransactionRepository {

    private val transactions = MutableLiveData<List<Transaction>>()

    private val accounts = listOf(
            Account("Наличные руб", 600.00, Currency.RUB),
            Account("Карта руб", 1600.00, Currency.RUB),
            Account("Карта доллары", 20600.00, Currency.USD))
    private val categories = listOf(
            Category("Зарплата", TransactionType.INCOME,
                    "ic_salary"),
            Category("Сбережения", TransactionType.INCOME,
                    "ic_saving"),
            Category("Транспорт", TransactionType.OUTCOME,
                    "ic_transport"),
            Category("Еда", TransactionType.OUTCOME,
                    "ic_food"))

    override fun getAll(): LiveData<List<Transaction>> {
        transactions.value = listOf(
                Transaction(accounts[0], 10000.00, TransactionType.INCOME, Date(), categories[0]),
                Transaction(accounts[1], 1000.00, TransactionType.OUTCOME, Date(), categories[1]),
                Transaction(accounts[2], 1000.00, TransactionType.OUTCOME, Date(), categories[2]),
                Transaction(accounts[0], 1000.00, TransactionType.INCOME, Date(), categories[3]),
                Transaction(accounts[1], 1000.00, TransactionType.OUTCOME, Date(), categories[0]))
        return transactions
    }

    override fun add(transaction: Transaction) {
        val mutableList = transactions.value?.toMutableList()
        mutableList?.add(transaction)
        transactions.value = mutableList
    }

    override fun delete(transaction: Transaction) {
        val mutableList = transactions.value?.toMutableList()
        mutableList?.remove(transaction)
        transactions.value = mutableList
    }
}