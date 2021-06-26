package com.nenartovich.example01_listview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat

class ListViewAdapter(private val mListObjects: List<ListObject>) : BaseAdapter() {
    override fun getCount(): Int = mListObjects.size

    override fun getItem(position: Int): ListObject = mListObjects[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater =  viewGroup?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.item, viewGroup, false)

        val tvText = v.findViewById<TextView>(R.id.tvText)
        val ivIcon = v.findViewById<ImageView>(R.id.ivIcon)
        val button = v.findViewById<Button>(R.id.button)

        val item = mListObjects[position]

        tvText.text = item.text
        ivIcon.setImageDrawable(ContextCompat.getDrawable(viewGroup.context, item.icon))

        button.setOnClickListener {
            Toast.makeText(v.context, item.text, Toast.LENGTH_SHORT).show()
        }
        return v
    }
}