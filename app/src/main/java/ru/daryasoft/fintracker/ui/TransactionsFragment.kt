package ru.daryasoft.fintracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_operation.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.viewmodel.TransactionsViewModel
import javax.inject.Inject

class TransactionsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_operation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transaction_list.layoutManager = LinearLayoutManager(context)

        getViewModel<TransactionsViewModel>(viewModelFactory)
                .getTransactions().observe(this,
                        Observer<List<Transaction>> { list ->
                            if (transaction_list.adapter == null) {
                                transaction_list.adapter = TransactionListAdapter(list ?: listOf())
                            }
                        }
                )
    }

    companion object {
        @JvmStatic
        fun newInstance() = TransactionsFragment()
    }
}
