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
        val item: View = convertView ?: layoutInflater.inflate(R.layout.book_item, parent, false)


        val ivCoverArt: ImageView = item.findViewById(R.id.iv_cover_art)
        val ivFavourite: ImageView = item.findViewById(R.id.iv_favourite)
        val tvBookName: TextView = item.findViewById(R.id.tv_book_name)
        val tvAuthor: TextView = item.findViewById(R.id.tv_book_author)

        ivCoverArt.setImageResource(book.imageResource)
        ivFavourite.setImageResource(if (book.isFavourite) R.drawable.star_enabled else R.drawable.star_disabled)
        tvBookName.text = mContext.resources.getString(book.name)
        tvAuthor.text = mContext.resources.getString(book.author)

        return item
    }
}