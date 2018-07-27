package ru.daryasoft.fintracker.ui.account

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_accounts.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.ui.getViewModel
import ru.daryasoft.fintracker.viewmodel.AccountsViewModel
import javax.inject.Inject

class AccountsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AccountsViewModel by lazy { getViewModel<AccountsViewModel>(viewModelFactory) }
    private val observer: Observer<List<Account>> by lazy {
        Observer<List<Account>> { list -> accountListAdapter.setData(list ?: listOf()) }
    }

    private val accountListAdapter = AccountListAdapter(listOf()) { position -> onDeleteAccount(position) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        account_list.layoutManager = LinearLayoutManager(context)
        account_list.adapter = accountListAdapter

        add_account.setOnClickListener { onAddAccount() }
    }

    override fun onStart() {
        super.onStart()
        viewModel.accounts.observe(this, observer)
    }

    override fun onStop() {
        super.onStop()
        viewModel.accounts.removeObserver(observer)
    }

    private fun onAddAccount() {
        OnAddAccountDialogFragment.newInstance { account -> viewModel.onAddAccount(account) }
                .show(fragmentManager, "OnAddAccountDialogFragment")
    }

    private fun onDeleteAccount(position: Int) {
        OnDeleteAccountDialogFragment.newInstance { viewModel.onDeleteAccount(position) }
                .show(fragmentManager, "OnDeleteAccountDialogFragment")
    }

    companion object {
        @JvmStatic
        fun newInstance() = AccountsFragment()
    }
}
