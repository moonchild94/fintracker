package ru.daryasoft.fintracker.transaction.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.daryasoft.fintracker.account.AccountRepository
import ru.daryasoft.fintracker.category.CategoryRepository
import ru.daryasoft.fintracker.entity.*
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Репозиторий для работы с финансовыми транзакциями.
 */
@Singleton
class TransactionRepositoryImpl @Inject constructor(accountRepository: AccountRepository,
                                                    categoryRepository: CategoryRepository) : TransactionRepository {

    private val transactions = MutableLiveData<List<Transaction>>()
    private val accounts = accountRepository.getAll()
    private val categories = categoryRepository.getAll()

    init {
        if (accounts.value != null && categories.value != null) {
            val accountsForGet = accounts.value as List<Account>
            val categoriesForGet = categories.value as List<Category>
            transactions.value = listOf(
                    Transaction(accountsForGet[0], Money(BigDecimal.valueOf(10000.00), accountsForGet[0].money.currency), Date(), categoriesForGet[0]),
                    Transaction(accountsForGet[1], Money(BigDecimal.valueOf(1000.00), accountsForGet[1].money.currency), Date(), categoriesForGet[1]),
                    Transaction(accountsForGet[2], Money(BigDecimal.valueOf(1000.00), accountsForGet[2].money.currency), Date(), categoriesForGet[2]),
                    Transaction(accountsForGet[0], Money(BigDecimal.valueOf(1000.00), accountsForGet[0].money.currency), Date(), categoriesForGet[3]),
                    Transaction(accountsForGet[0], Money(BigDecimal.valueOf(2000.00), accountsForGet[0].money.currency), Date(), categoriesForGet[3]),
                    Transaction(accountsForGet[0], Money(BigDecimal.valueOf(3000.00), accountsForGet[0].money.currency), Date(), categoriesForGet[3]),
                    Transaction(accountsForGet[0], Money(BigDecimal.valueOf(1000.00), accountsForGet[0].money.currency), Date(), categoriesForGet[2]),
                    Transaction(accountsForGet[0], Money(BigDecimal.valueOf(200.00), accountsForGet[0].money.currency), Date(), categoriesForGet[2]),
                    Transaction(accountsForGet[1], Money(BigDecimal.valueOf(1000.00), accountsForGet[1].money.currency), Date(), categoriesForGet[0]))
        }
    }

    override fun getAll(): LiveData<List<Transaction>> {

        return transactions
    }

    override fun query(category: Category, account: Account?): LiveData<List<Transaction>> {
        val filteredValues = MutableLiveData<List<Transaction>>()
        filteredValues.value = transactions.value?.filter { it.category == category && (account == null || it.account == account) } ?: listOf()
        return filteredValues
    }

    override fun add(transaction: Transaction) {
        val mutableList = transactions.value?.toMutableList()
        mutableList?.add(transaction)
        transactions.value = mutableList

        transaction.account.money.value.add( if (transaction.category.transactionType == TransactionType.INCOME)
           transaction.sum.value else -transaction.sum.value)
    }

    override fun delete(transaction: Transaction) {
        val mutableList = transactions.value?.toMutableList()
        mutableList?.remove(transaction)
        transactions.value = mutableList
    }
}