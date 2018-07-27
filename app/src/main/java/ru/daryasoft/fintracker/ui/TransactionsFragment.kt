package ru.daryasoft.fintracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_transactions.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.viewmodel.TransactionsViewModel
import javax.inject.Inject

/**
 * Фрагмент для списка транзакций.
 */
class TransactionsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: TransactionsViewModel by lazy { getViewModel<TransactionsViewModel>(viewModelFactory) }
    private val observer: Observer<List<Transaction>> by lazy {
        Observer<List<Transaction>> { list -> transactionListAdapter.setData(list ?: listOf()) }
    }

    private val transactionListAdapter = TransactionListAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transaction_list.layoutManager = LinearLayoutManager(context)
        transaction_list.adapter = transactionListAdapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.transactions.observe(this, observer)
    }

    override fun onStop() {
        viewModel.transactions.removeObserver(observer)
        super.onStop()
    }

    companion object {
        @JvmStatic
        fun newInstance() = TransactionsFragment()
    }
}
