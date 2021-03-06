package ru.daryasoft.fintracker.transaction

/**
 * Слушатель события добавления транзакций.
 */
interface AddTransactionListener {

    /**
     * Реакция на событие открытия диалога с добавлением транзакции.
     */
    fun onAddTransactionOpen()

    /**
     * Реакция на событие успешного добавления транзакции.
     */
    fun onAddTransactionComplete()
}