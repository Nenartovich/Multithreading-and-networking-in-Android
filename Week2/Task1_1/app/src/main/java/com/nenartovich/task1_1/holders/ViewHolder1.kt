package com.nenartovich.task1_1.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.task1_1.R
import java.lang.Exception

class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
    fun bind(title: String, description: String) {
        tvTitle.text = title
        tvDescription.text = description
    }

}