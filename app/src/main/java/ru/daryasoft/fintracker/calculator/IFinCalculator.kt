package ru.daryasoft.fintracker.calculator

import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.Transaction

/**
 * Сервис для выполнения финансовых расчетов.
 */
interface IFinCalculator {
    /**
     * Подсчитывает итоговую сумму для переданных транзакций.
     *
     * @param transactions список транзакций.
     * @param targetCurrency валюта, в которой требуется подсчитать итоговую сумму.
     *
     * @return итоговая сумма.
     */
    fun sum(transactions: List<Transaction>, targetCurrency: Currency): Balance
}