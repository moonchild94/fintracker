package ru.daryasoft.fintracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.repository.AccountRepository
import javax.inject.Inject

/**
 * ViewModel для списка счетов.
 */
class AccountsViewModel @Inject constructor(private val accountRepository: AccountRepository) : ViewModel() {
    val accounts: LiveData<List<Account>> by lazy {
        accountRepository.getAll()
    }

    fun onAddAccount(account: Account) {
        accountRepository.add(account)
    }

    fun onDeleteAccount(position: Int) {
        accounts.value?.get(position)?.let { accountRepository.delete(it) }
    }
}