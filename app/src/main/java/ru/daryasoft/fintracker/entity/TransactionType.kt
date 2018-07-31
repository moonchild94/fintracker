package ru.daryasoft.fintracker.entity

import ru.daryasoft.fintracker.R

/**
 * Тип финансовой транзакции.
 */
enum class TransactionType(val resId: Int) {
    INCOME(R.string.income), OUTCOME(R.string.outcome);
}