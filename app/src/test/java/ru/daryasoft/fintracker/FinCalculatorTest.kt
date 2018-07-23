package ru.daryasoft.fintracker

import com.nhaarman.mockito_kotlin.any
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import ru.daryasoft.fintracker.calculator.FinCalculator
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.entity.TransactionType
import ru.daryasoft.fintracker.repository.ICurrencyRepository
import java.util.*

class FinCalculatorTest {
    @Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var currencyRepository: ICurrencyRepository

    @InjectMocks
    private lateinit var finCalculator: FinCalculator

    @Test
    fun rubAndTransactionsInDifferentCurrency_sum_returnsExpectedSum() {
        Mockito.`when`(currencyRepository.getRate(Currency.RUB, Currency.RUB, any())).thenReturn(1.00)
        Mockito.`when`(currencyRepository.getRate(Currency.USD, Currency.USD, any())).thenReturn(1.00)
        Mockito.`when`(currencyRepository.getRate(Currency.RUB, Currency.USD, any())).thenReturn(0.02)
        Mockito.`when`(currencyRepository.getRate(Currency.USD, Currency.RUB, any())).thenReturn(50.00)

        val transactions = listOf(
                Transaction(Currency.RUB, 10000.00, TransactionType.INCOME, Date()),
                Transaction(Currency.RUB, 1000.00, TransactionType.OUTCOME, Date()),
                Transaction(Currency.RUB, 1000.00, TransactionType.OUTCOME, Date()),
                Transaction(Currency.USD, 1000.00, TransactionType.INCOME, Date()),
                Transaction(Currency.USD, 1000.00, TransactionType.OUTCOME, Date()))

        finCalculator.sum(transactions, Currency.RUB)
    }
}