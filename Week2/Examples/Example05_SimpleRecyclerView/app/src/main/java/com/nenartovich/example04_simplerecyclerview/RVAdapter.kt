package com.nenartovich.example04_simplerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView

class RVAdapter : RecyclerView.Adapter<ListObjectViewHolder>() {

    companion object {
        val mListObjects = arrayOf("Android 1", "Android 2", "Android 3",
            "Android 4", "Android 5", "Android 6",
            "Android 7", "Android 8", "Android 9",
            "Android 10", "Android 11", "Android 12",
            "Android 13", "Android 14", "Android 15",
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListObjectViewHolder {
        return ListObjectViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListObjectViewHolder, position: Int) {
        holder.bind(mListObjects[position])
    }

    override fun getItemCount(): Int = mListObjects.size

}