package ru.daryasoft.fintracker.transaction

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat.getDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.transaction_item.view.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.entity.TransactionType


/**
 * Адаптер для отображения списка транзакций.
 */
class TransactionListAdapter(private var transactions: List<Transaction>,
                             private val onDeleteAction: (position: Int) -> Unit) : RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        val uri = Uri.parse("android.resource://ru.daryasoft.fintracker/drawable/" + transaction.category.iconUri)
        holder.categoryIcon.setImageURI(uri)
        holder.transactionType.setImageDrawable(holder.transactionType.context.resources.getDrawable(getIconForTransactionType(transaction.category.transactionType)))
        holder.transactionSum.text = transaction.sum.toString()
        holder.transactionCurrency.text = transaction.account.currency.name
        holder.transactionDate.text = getDateFormat(holder.transactionDate.context).format(transaction.date)
        holder.categoryName.text = transaction.category.name
        holder.accountName.text = transaction.account.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return ViewHolder(root)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun setData(changedTransactions: List<Transaction>) {
        transactions = changedTransactions
        notifyDataSetChanged()
    }

    private fun getIconForTransactionType(transactionType: TransactionType): Int {
        return when (transactionType) {
            TransactionType.OUTCOME -> R.drawable.ic_chevron_left_black_24dp
            TransactionType.INCOME -> R.drawable.ic_chevron_right_black_24dp
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryIcon: ImageView = itemView.category_icon
        var transactionType: ImageView = itemView.transaction_type
        var transactionSum: TextView = itemView.transaction_sum
        var transactionCurrency: TextView = itemView.transaction_currency
        var transactionDate: TextView = itemView.transaction_date
        var categoryName: TextView = itemView.category_name
        var accountName: TextView = itemView.account_name

        init {
            itemView.setOnLongClickListener {
                onDeleteAction.invoke(adapterPosition)
                true
            }
        }
    }
}