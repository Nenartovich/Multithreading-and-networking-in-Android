package com.nenartovich.example07_heterogeneousrv.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.example07_heterogeneousrv.R

class TextViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val textView: TextView = item.findViewById(R.id.tv_text)

    fun bind() {
        textView.text = "My favourite text"
    }
}