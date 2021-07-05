package com.nenartovich.example07_heterogeneousrv.holders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.example07_heterogeneousrv.R

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.iv_image)

    fun bind() {
        imageView.setImageResource(R.drawable.picture)
    }

}
