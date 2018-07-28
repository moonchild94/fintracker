package ru.daryasoft.fintracker.ui.transaction

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomArrayAdapter<T>(context: Context?, private val data: List<T>, private val toStringFun: (element: T) -> String)
    : ArrayAdapter<T>(context, android.R.layout.simple_spinner_item, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getView(position, convertView, parent) as TextView
        view.text = toStringFun.invoke(data[position])
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =  super.getDropDownView(position, convertView, parent) as TextView
        view.text = toStringFun.invoke(data[position])
        return view
    }
}