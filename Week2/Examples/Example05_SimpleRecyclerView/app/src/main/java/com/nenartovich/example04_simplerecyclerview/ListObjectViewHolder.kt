package com.nenartovich.example04_simplerecyclerview

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        val tvText: TextView = itemView.findViewById(R.id.tvText)
        val btnClick: Button = itemView.findViewById(R.id.btnClick)

    fun bind(name: String) {
        ivImage.setImageResource(R.drawable.ic_android_black_24dp)
        tvText.text = name
        btnClick.setOnClickListener { view -> onClick(view) }
    }

    fun onClick(view: View) {
        Toast.makeText(view.context, tvText.text, Toast.LENGTH_SHORT).show()
    }
}