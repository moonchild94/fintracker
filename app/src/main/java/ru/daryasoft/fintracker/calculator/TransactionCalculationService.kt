package ru.daryasoft.fintracker.calculator

import ru.daryasoft.fintracker.entity.*

/**
 * Сервис для выполнения финансовых расчетов.
 */
interface TransactionCalculationService {
    /**
     * Подсчитывает итоговую сумму для переданных транзакций.
     *
     * @param transactions список транзакций.
     * @param targetCurrency валюта, в которой требуется подсчитать итоговую сумму.
     *
     * @return итоговая сумма.
     */
    fun aggregateByCategories(account: Account?, categories: List<Category>, targetCurrency: Currency): List<TransactionAggregateInfo>
}