package ru.daryasoft.fintracker.category

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.entity.Category
import ru.daryasoft.fintracker.entity.TransactionType
import ru.daryasoft.fintracker.category.CategoryRepository
import javax.inject.Inject

/**
 * ViewModel для списка категорий.
 */
class CategoriesViewModel @Inject constructor(private val categoryRepository: CategoryRepository) : ViewModel() {

    val categories: LiveData<List<Category>> by lazy {
        categoryRepository.getAll()
    }

    fun getCategoriesByType(transactionType: TransactionType) : LiveData<List<Category>> {
        return categoryRepository.findByTransactionType(transactionType)
    }
}