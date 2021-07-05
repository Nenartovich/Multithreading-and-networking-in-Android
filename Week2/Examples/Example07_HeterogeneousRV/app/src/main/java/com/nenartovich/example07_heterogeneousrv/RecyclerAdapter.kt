package com.nenartovich.example07_heterogeneousrv

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.example07_heterogeneousrv.holders.ImageViewHolder
import com.nenartovich.example07_heterogeneousrv.holders.TextViewHolder

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == 0) {
            val view = layoutInflater.inflate(R.layout.item1, parent, false)
            viewHolder = TextViewHolder(view)
        } else {
            val view = layoutInflater.inflate(R.layout.item2, parent, false)
            viewHolder = ImageViewHolder(view)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            holder.bind()
        } else if (holder is TextViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount(): Int = 10

    override fun getItemViewType(position: Int) = if (position % 2 == 0) 0 else 1
}