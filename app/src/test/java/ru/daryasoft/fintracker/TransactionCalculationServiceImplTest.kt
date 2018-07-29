package ru.daryasoft.fintracker

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import ru.daryasoft.fintracker.calculator.TransactionCalculationServiceImpl
import ru.daryasoft.fintracker.entity.Balance
import ru.daryasoft.fintracker.entity.Currency
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.entity.TransactionType
import ru.daryasoft.fintracker.rate.RateRepository
import java.util.*

/**
 * Unit-тесты для сервиса для выполнения финансовых расчетов.
 */
class TransactionCalculationServiceImplTest {
    @JvmField
    @Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var rateRepository: RateRepository

    @InjectMocks
    private lateinit var transactionCalculationServiceImpl: TransactionCalculationServiceImpl

    private val transactions = listOf(
            Transaction(Currency.RUB, 10000.00, TransactionType.INCOME, Date()),
            Transaction(Currency.RUB, 1000.00, TransactionType.OUTCOME, Date()),
            Transaction(Currency.RUB, 1000.00, TransactionType.OUTCOME, Date()),
            Transaction(Currency.USD, 1000.00, TransactionType.INCOME, Date()),
            Transaction(Currency.USD, 1000.00, TransactionType.OUTCOME, Date()))

    @Test
    fun rubAndTransactionsInDifferentCurrency_sum_returnsExpectedSum() {
        Mockito.`when`(rateRepository.getRateToDefault(eq(Currency.RUB), eq(Currency.RUB), any())).thenReturn(1.00)
        Mockito.`when`(rateRepository.getRateToDefault(eq(Currency.USD), eq(Currency.RUB), any())).thenReturn(50.00)

        val expectedBalance = Balance(Currency.RUB, 8000.0)

        val actualBalance = transactionCalculationServiceImpl.aggregateByCategories(transactions, Currency.RUB)

        Assert.assertEquals(expectedBalance, actualBalance)
    }

    @Test
    fun usdAndTransactionsInDifferentCurrency_sum_returnsExpectedSum() {
        Mockito.`when`(rateRepository.getRateToDefault(eq(Currency.USD), eq(Currency.USD), any())).thenReturn(1.00)
        Mockito.`when`(rateRepository.getRateToDefault(eq(Currency.RUB), eq(Currency.USD), any())).thenReturn(0.02)

        val expectedBalance = Balance(Currency.USD, 160.0)

        val actualBalance = transactionCalculationServiceImpl.aggregateByCategories(transactions, Currency.USD)

        Assert.assertEquals(expectedBalance, actualBalance)
    }
}