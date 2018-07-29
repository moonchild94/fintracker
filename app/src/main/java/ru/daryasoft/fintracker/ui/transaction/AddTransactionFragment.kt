package ru.daryasoft.fintracker.ui.transaction

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.entity.Category
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.entity.TransactionType
import ru.daryasoft.fintracker.ui.getViewModel
import ru.daryasoft.fintracker.viewmodel.AccountsViewModel
import ru.daryasoft.fintracker.viewmodel.CategoriesViewModel
import ru.daryasoft.fintracker.viewmodel.TransactionsViewModel
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
        transaction_type.setOnCheckedChangeListener { _, isChecked ->
            category_spinner.adapter = CustomArrayAdapter(context,
                    categoriesViewModel.getCategoriesByType(
                            if (isChecked) TransactionType.INCOME else TransactionType.OUTCOME).value
                            ?: listOf())
            { category -> category.name }
        }
    }

    private fun initTransactionDateSelector() {
        transaction_date_selector.setOnClickListener {
            val currentDate = Calendar.getInstance()
            DatePickerDialog(activity,
                    DatePickerDialog.OnDateSetListener { p0, year, month, dayOfMonth ->
                        val date = Calendar.getInstance()
                        date.set(Calendar.YEAR, year)
                        date.set(Calendar.MONTH, month)
                        date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        transaction_date.text = DateFormat.getDateFormat(context).format(date.time)
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
        add_transaction_ok.setOnClickListener {
            val account = transaction_account_spinner.selectedItem as Account
            val transactionSum = transaction_sum.text.toString().toDouble()
            val date = Date()
            val category = category_spinner.selectedItem as Category
            val transaction = Transaction(account, transactionSum, date, category)
            transactionsViewModel.onAddTransaction(transaction)
            addTransactionListener?.onAddTransactionComplete()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): AddTransactionFragment = AddTransactionFragment()
    }
}