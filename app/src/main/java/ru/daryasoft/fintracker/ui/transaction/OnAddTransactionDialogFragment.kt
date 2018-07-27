package ru.daryasoft.fintracker.ui.transaction

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Transaction

/**
 * Диалог, который отображается при создании счета.
 */
class OnAddTransactionDialogFragment : DialogFragment() {
    private lateinit var onAddTransactionAction: (transition: Transaction) -> Unit
    private val dialogView by lazy { activity?.layoutInflater?.inflate(R.layout.dialog_add_account, null) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle("Добавить транзакцию")
                .setPositiveButton("OK") { _, _ -> }//onAddTransactionAction.invoke(onAddTransaction()) }
                .setNegativeButton("Cancel") { _, _ -> }
        return dialogBuilder.create()
    }

    private fun onAddTransaction(): Transaction? {
        return null
    }

    companion object {
        @JvmStatic
        fun newInstance(onAddAction: (transaction: Transaction) -> Unit): OnAddTransactionDialogFragment {
            val onAddTransactionDialogFragment = OnAddTransactionDialogFragment()
            onAddTransactionDialogFragment.onAddTransactionAction = onAddAction
            return onAddTransactionDialogFragment
        }
    }
}