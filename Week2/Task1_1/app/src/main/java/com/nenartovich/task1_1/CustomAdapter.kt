package com.nenartovich.task1_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.task1_1.holders.ViewHolder1
import com.nenartovich.task1_1.holders.ViewHolder2
import com.nenartovich.task1_1.items.ImageItem
import com.nenartovich.task1_1.items.TextItem

class CustomAdapter(private val data: MutableList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            val view = layoutInflater.inflate(R.layout.item1, parent, false)
            ViewHolder1(view)
        } else {
            val view = layoutInflater.inflate(R.layout.item2, parent, false)
            ViewHolder2(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder1) {
            val title = (data[position] as TextItem).title
            val description = (data[position] as TextItem).description
            holder.bind(title, description)
            holder.setListener(listener)
        } else {
            (holder as ViewHolder2).bind((data[position] as ImageItem).pictureId)
            holder.setListener(listener)
        }

    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = if (data[position] is TextItem) 0 else 1

    fun setListener(listener: OnItemClickListener?) {
        this.listener = listener!!
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}