package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.daryasoft.fintracker.entity.Category
import ru.daryasoft.fintracker.entity.TransactionType
import javax.inject.Inject

/**
 * Репозиторий для работы с категориями.
 */
class CategoryRepositoryImpl @Inject constructor(): CategoryRepository {

    private val categories = MutableLiveData<List<Category>>()

    override fun getAll(): LiveData<List<Category>> {
        categories.value = mutableListOf(
                Category("Зарплата", TransactionType.INCOME,
                        "ic_salary"),
                Category("Сбережения", TransactionType.INCOME,
                        "ic_saving"),
                Category("Транспорт", TransactionType.OUTCOME,
                        "ic_transport"),
                Category("Еда", TransactionType.OUTCOME,
                        "ic_food"))
        return categories
    }

    override fun findByTransactionType(transactionType: TransactionType): LiveData<List<Category>> {
        val categoriesByType = MutableLiveData<List<Category>>()
        categoriesByType.value = getAll().value?.filter { it.transactionType == transactionType }
        return categoriesByType
    }
}