package com.nenartovich.task1_1.holders

import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.task1_1.CustomAdapter
import com.nenartovich.task1_1.R
import java.lang.Exception

class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivImage: ImageView = itemView.findViewById(R.id.iv_image)
    fun bind(resourceId: Int) {
       ivImage.setImageResource(resourceId)
    }

    fun setListener(listener: CustomAdapter.OnItemClickListener) {
        itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }
}