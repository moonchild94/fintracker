package ru.daryasoft.fintracker.repository

import android.arch.lifecycle.LiveData
import ru.daryasoft.fintracker.entity.Category

/**
 * Репозиторий для работы с категориями.
 */
interface CategoryRepository {
    fun getAll(): LiveData<List<Category>>
}