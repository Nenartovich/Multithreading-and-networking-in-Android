package com.nenartovich.example04_gridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class BooksAdapter(private val mContext: Context, private val books: Array<Book>) : BaseAdapter() {
    override fun getCount(): Int = books.size

    override fun getItem(position: Int): Any = books[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val book = getItem(position) as Book
        val layoutInflater = LayoutInflater.from(mContext)
        val item: View
        if (convertView == null) {
            item = layoutInflater.inflate(R.layout.book_item, parent, false)

            val ivCoverArt: ImageView = item.findViewById(R.id.iv_cover_art)
            val ivFavourite: ImageView = item.findViewById(R.id.iv_favourite)
            val tvBookName: TextView = item.findViewById(R.id.tv_book_name)
            val tvAuthor: TextView = item.findViewById(R.id.tv_book_author)

            item.tag = ViewHolder(ivCoverArt, ivFavourite, tvBookName, tvAuthor)
        } else {
            item = convertView
        }

        val holder = item.tag as ViewHolder


        holder.ivCoverArt.setImageResource(book.imageResource)
        holder.ivFavourite.setImageResource(if (book.isFavourite) R.drawable.star_enabled else R.drawable.star_disabled)
        holder.tvBookName.text = mContext.resources.getString(book.name)
        holder.tvAuthor.text = mContext.resources.getString(book.author)

        return item
    }

    private class ViewHolder(val ivCoverArt: ImageView, val ivFavourite: ImageView,
                                val tvBookName: TextView, val tvAuthor: TextView)
}