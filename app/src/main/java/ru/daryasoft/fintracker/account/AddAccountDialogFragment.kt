package ru.daryasoft.fintracker.account

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.dialog_add_account.view.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Account
import ru.daryasoft.fintracker.entity.Currency

/**
 * Диалог, который отображается при создании счета.
 */
class AddAccountDialogFragment : DialogFragment() {
    private lateinit var onAddAccountAction: (account: Account) -> Unit

    private val dialogView by lazy { activity?.layoutInflater?.inflate(R.layout.dialog_add_account, null) }
    private val dialog by lazy {
        AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle("Добавить счет")
                .setPositiveButton("OK") { _, _ -> onAddAccountAction.invoke(onAddAccount()) }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView?.account_currency_spinner?.adapter = ArrayAdapter<Currency>(context, android.R.layout.simple_spinner_item, Currency.values())

        dialogView?.account_name?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = text != null && text.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
    }

    private fun onAddAccount(): Account {
        val accountName = dialogView?.account_name?.text.toString()
        val startAmount = dialogView?.account_start_amount?.text.toString()
        val currency = dialogView?.account_currency_spinner?.selectedItem as Currency
        return Account(accountName, if (startAmount.isEmpty()) 0.0 else startAmount.toDouble(), currency)
    }

    companion object {
        @JvmStatic
        fun newInstance(onAddAction: (account: Account) -> Unit): AddAccountDialogFragment {
            val onAddAccountDialogFragment = AddAccountDialogFragment()
            onAddAccountDialogFragment.onAddAccountAction = onAddAction
            return onAddAccountDialogFragment
        }
    }
}