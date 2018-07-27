package ru.daryasoft.fintracker.ui.account

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.dialog_add_account.view.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.entity.Currency

/**
 * Диалог, который отображается при создании счета.
 */
class OnAddAccountDialogFragment : DialogFragment() {
    private lateinit var onAddAccountAction: (account: Account) -> Unit
    private val dialogView by lazy { activity?.layoutInflater?.inflate(R.layout.dialog_add_account, null) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView?.account_currency_spinner?.adapter = ArrayAdapter<Currency>(context, android.R.layout.simple_spinner_item, Currency.values())
        val dialogBuilder = AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle("Добавить счет")
                .setPositiveButton("OK") { _, _ -> onAddAccountAction.invoke(onAddAccount()) }
                .setNegativeButton("Cancel") { _, _ -> }
        return dialogBuilder.create()
    }

    private fun onAddAccount(): Account {
        val accountName = dialogView?.account_name?.text.toString()
        val startAmount = dialogView?.account_start_amount?.text.toString().toDouble()
        val currency = dialogView?.account_currency_spinner?.selectedItem as Currency
        return Account(accountName, startAmount, currency)
    }

    companion object {
        @JvmStatic
        fun newInstance(onAddAction: (account: Account) -> Unit): OnAddAccountDialogFragment {
            val onAddAccountDialogFragment = OnAddAccountDialogFragment()
            onAddAccountDialogFragment.onAddAccountAction = onAddAction
            return onAddAccountDialogFragment
        }
    }
}