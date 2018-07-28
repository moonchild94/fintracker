package ru.daryasoft.fintracker.ui.transaction

interface AddTransactionListener {
    fun onAddTransactionOpen()

    fun onAddTransactionComplete()
}