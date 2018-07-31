package ru.daryasoft.fintracker.transaction

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.account.AccountsViewModel
import ru.daryasoft.fintracker.category.CategoriesViewModel
import ru.daryasoft.fintracker.common.CustomArrayAdapter
import ru.daryasoft.fintracker.common.getViewModel
import ru.daryasoft.fintracker.common.hideKeyboard
import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.entity.Category
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.entity.TransactionType
import java.util.*
import javax.inject.Inject

/**
 * Фрагмент, который отображается при создании счета.
 */
class AddTransactionFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val transactionsViewModel: TransactionsViewModel by lazy { getViewModel<TransactionsViewModel>(viewModelFactory) }
    private val categoriesViewModel: CategoriesViewModel by lazy { getViewModel<CategoriesViewModel>(viewModelFactory) }
    private val accountsViewModel: AccountsViewModel by lazy { getViewModel<AccountsViewModel>(viewModelFactory) }

    private var addTransactionListener: AddTransactionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_fragment_add_transaction)
        return layoutInflater.inflate(R.layout.fragment_add_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTransactionTypeSwitcher()
        initTransactionDateSelector()
        initAccountSpinner()
        initCategorySpinner()
        initOkButton()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is AddTransactionListener) {
            throw IllegalArgumentException()
        }
        addTransactionListener = context
    }

    override fun onDetach() {
        addTransactionListener = null
        super.onDetach()
    }

    private fun initTransactionTypeSwitcher() {
        transaction_type_spinner.adapter = CustomArrayAdapter(context,
                TransactionType.values().toList()) { transactionType -> getString(transactionType.resId) }

        transaction_type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                category_spinner.adapter = CustomArrayAdapter(context,
                        categoriesViewModel.getCategoriesByType(transaction_type_spinner.selectedItem as TransactionType).value
                                ?: listOf())
                { category -> category.name }
            }
        }
    }

    private fun initTransactionDateSelector() {
        transaction_date_selector.text = DateFormat.getDateFormat(context).format(Date())

        transaction_date_selector.setOnClickListener {
            val currentDate = Calendar.getInstance()
            DatePickerDialog(activity,
                    DatePickerDialog.OnDateSetListener { p0, year, month, dayOfMonth ->
                        val date = Calendar.getInstance()
                        date.set(Calendar.YEAR, year)
                        date.set(Calendar.MONTH, month)
                        date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        transaction_date_selector.text = DateFormat.getDateFormat(context).format(date.time)
                    },
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH))
                    .show()
        }
    }

    private fun initAccountSpinner() {
        transaction_account_spinner.adapter = CustomArrayAdapter(context, accountsViewModel.accounts.value
                ?: listOf()) { account -> account.name }
        transaction_account_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                transaction_currency.text = (transaction_account_spinner.selectedItem as Account).currency.name
            }
        }
    }

    private fun initCategorySpinner() {
        category_spinner.adapter = CustomArrayAdapter(context,
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
            val transaction = Transaction(account, transactionSum, date, category)
            transactionsViewModel.onAddTransaction(transaction)
            addTransactionListener?.onAddTransactionComplete()
            hideKeyboard(transaction_amount)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): AddTransactionFragment = AddTransactionFragment()
    }
}