package ru.daryasoft.fintracker.account

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.account_item.view.*
import ru.daryasoft.fintracker.R
import ru.daryasoft.fintracker.entity.Account

/**
 * Адаптер для отображения списка счетов.
 */
class AccountListAdapter(private var accounts: List<Account>,
                         private val onDeleteAction: (position: Int) -> Unit) : RecyclerView.Adapter<AccountListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = accounts[position].name
        holder.amount.text = accounts[position].amount.toString()
        holder.currency.text = accounts[position].currency.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.account_item, parent, false))
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    fun setData(changedAccounts: List<Account>) {
        accounts = changedAccounts
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.accountName
        val amount: TextView = itemView.accountBalance
        val currency: TextView = itemView.accountCurrency

        init {
            itemView.setOnLongClickListener {
                onDeleteAction.invoke(adapterPosition)
                true
            }
        }
    }
}