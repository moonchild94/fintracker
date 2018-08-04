package ru.daryasoft.fintracker.transaction.ui

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.View
import android.widget.AdapterView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_add_transaction.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.account.AccountsViewModel
import ru.daryasoft.fintracker.category.CategoriesViewModel
import ru.daryasoft.fintracker.common.CustomArrayAdapter
import ru.daryasoft.fintracker.common.getViewModel
import ru.daryasoft.fintracker.common.hideKeyboard
import ru.daryasoft.fintracker.entity.*
import ru.daryasoft.fintracker.transaction.viewModel.TransactionsViewModel
import java.util.*
import javax.inject.Inject

/**
 * Фрагмент, который отображается при создании счета.
 */
class AddTransactionActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val transactionsViewModel: TransactionsViewModel by lazy { getViewModel<TransactionsViewModel>(viewModelFactory) }
    private val categoriesViewModel: CategoriesViewModel by lazy { getViewModel<CategoriesViewModel>(viewModelFactory) }
    private val accountsViewModel: AccountsViewModel by lazy { getViewModel<AccountsViewModel>(viewModelFactory) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        title = getString(R.string.title_fragment_add_transaction)

        val supportActionBar = supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initTransactionTypeSwitcher()
        initTransactionDateSelector()
        initAccountSpinner()
        initCategorySpinner()
        initOkButton()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initTransactionTypeSwitcher() {
        transaction_type_spinner.adapter = CustomArrayAdapter(this,
                TransactionType.values().toList()) { transactionType -> getString(transactionType.resId) }

        transaction_type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                category_spinner.adapter = CustomArrayAdapter(applicationContext,
                        categoriesViewModel.getCategoriesByType(transaction_type_spinner.selectedItem as TransactionType).value
                                ?: listOf()
                ) { category -> category.name }


            }
        }
    }

    private fun initTransactionDateSelector() {
        transaction_date_selector.text = DateFormat.getDateFormat(this).format(Date())

        transaction_date_selector.setOnClickListener {
            val currentDate = Calendar.getInstance()
            DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { p0, year, month, dayOfMonth ->
                        val date = Calendar.getInstance()
                        date.set(Calendar.YEAR, year)
                        date.set(Calendar.MONTH, month)
                        date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        transaction_date_selector.text = DateFormat.getDateFormat(this).format(date.time)
                    },
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH))
                    .show()
        }
    }

    private fun initAccountSpinner() {
        transaction_account_spinner.adapter = CustomArrayAdapter(this, accountsViewModel.accounts.value
                ?: listOf()) { account -> account.name }
        transaction_account_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                transaction_currency.text = (transaction_account_spinner.selectedItem as Account).money.currency.name
            }
        }
    }

    private fun initCategorySpinner() {
        category_spinner.adapter = CustomArrayAdapter(this,
                categoriesViewModel.getCategoriesByType(TransactionType.OUTCOME).value ?: listOf())
        { category -> category.name }
    }

    private fun initOkButton() {
        transaction_amount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                add_transaction_ok.isEnabled = text != null && text.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        add_transaction_ok.setOnClickListener {
            val account = transaction_account_spinner.selectedItem as Account
            val transactionSum = transaction_amount.text.toString().toDouble()
            val date = Date()
            val category = category_spinner.selectedItem as Category
            val transaction = Transaction(account, Money(transactionSum.toBigDecimal(), account.money.currency), date, category)
            transactionsViewModel.onAddTransaction(transaction)
            hideKeyboard(transaction_amount)
            onBackPressed()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): AddTransactionActivity = AddTransactionActivity()
    }
}