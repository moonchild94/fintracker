package ru.daryasoft.fintracker.ui.transaction

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

/**
 * Диалог, который отображается для подтверждения удаления счета.
 */
class OnDeleteTransactionDialogFragment : DialogFragment() {
    private lateinit var onDeleteTransaction: () -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(context)
                .setTitle("Вы действительно хотите удалить операцию?")
                .setPositiveButton("OK") { _, _ -> onDeleteTransaction.invoke() }
                .setNegativeButton("Cancel") { _, _ -> this@OnDeleteTransactionDialogFragment.dialog.cancel() }
        return dialogBuilder.create()
    }

    companion object {
        @JvmStatic
        fun newInstance(onDeleteTransaction: () -> Unit): OnDeleteTransactionDialogFragment {
            val onDeleteTransactionDialogFragment = OnDeleteTransactionDialogFragment()
            onDeleteTransactionDialogFragment.onDeleteTransaction = onDeleteTransaction
            return onDeleteTransactionDialogFragment
        }
    }
}