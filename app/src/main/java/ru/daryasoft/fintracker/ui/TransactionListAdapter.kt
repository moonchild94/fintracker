package ru.daryasoft.fintracker.ui

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat.getDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Transaction
import ru.daryasoft.fintracker.entity.TransactionType

/**
 * Адаптер для отображения списка транзакций.
 */
class TransactionListAdapter(private val transactions: List<Transaction>) : RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.date.text = getDateFormat(holder.date.context).format(transactions[position].date)
        holder.sum.text = transactions[position].sum.toString()
        holder.currency.text = transactions[position].currency.toString()
        holder.operationType.setImageResource(getIconForTransactionType(transactions[position].type))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return ViewHolder(root)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    private fun getIconForTransactionType(transactionType: TransactionType): Int {
        return when (transactionType) {
            TransactionType.OUTCOME -> R.drawable.ic_chevron_left_black_24dp
            TransactionType.INCOME -> R.drawable.ic_chevron_right_black_24dp
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date: TextView = itemView.findViewById(R.id.date)
        var sum: TextView = itemView.findViewById(R.id.sum)
        var currency: TextView = itemView.findViewById(R.id.currency)
        var operationType: ImageView = itemView.findViewById(R.id.operation_type)
    }
}