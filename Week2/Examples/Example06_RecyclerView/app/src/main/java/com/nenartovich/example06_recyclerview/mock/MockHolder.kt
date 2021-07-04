package com.nenartovich.example06_recyclerview.mock

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.example06_recyclerview.ContactsAdapter
import com.nenartovich.example06_recyclerview.R

class MockHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvName: TextView = itemView.findViewById(R.id.tv_name)
    private val tvValue: TextView = itemView.findViewById(R.id.tv_value)
    private var id: Int = -1

    fun bind(data: Mock) {
        tvName.text = data.name
        tvValue.text = data.value.toString()
        id = data.value
    }

    fun setListener(listener: ContactsAdapter.OnItemClickListener?) {
        itemView.setOnClickListener {
            listener!!.onItemClick(id)
        }
    }
}