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
                        "android.resource://ru.daryasoft.fintracker/drawable/ic_salary"),
                Category("Сбережения", TransactionType.INCOME,
                        "android.resource://ru.daryasoft.fintracker/drawable/ic_savings"),
                Category("Транспорт", TransactionType.OUTCOME,
                        "android.resource://ru.daryasoft.fintracker/drawable/ic_transport"),
                Category("Еда", TransactionType.OUTCOME,
                        "android.resource://ru.daryasoft.fintracker/drawable/ic_food"))
        return categories
    }
}