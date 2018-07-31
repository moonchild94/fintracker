package ru.daryasoft.fintracker.calculator

import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.entity.Category
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.TransactionAggregateInfo

/**
 * Сервис для выполнения финансовых расчетов.
 */
interface TransactionCalculationService {
    /**
     * Подсчитывает итоговую сумму по категориям.
     *
     * @param account счет.
     * @categories список категорий
     * @param targetCurrency валюта, в которой требуется подсчитать итоговую сумму.
     *
     * @return итоговая сумма.
     */
    fun aggregateByCategories(account: Account?, categories: List<Category>, targetCurrency: Currency): List<TransactionAggregateInfo>
}