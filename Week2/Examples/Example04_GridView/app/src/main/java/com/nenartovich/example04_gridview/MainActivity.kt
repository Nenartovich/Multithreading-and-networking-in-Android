package com.nenartovich.example04_gridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val FAVOURITE_BOOKS_NAMES_KEY = "com.nenartovich.FAVOURITE_BOOKS_NAMES_KEY"
        private val bookArray  = arrayOf(
        Book(R.string.abc_an_amazing_alphabet_book, R.string.dr_seuss, R.drawable.abc,
        false, "https://images-na.ssl-images-amazon.com/images/I/51ZptZg2ziL._SX359_BO1,204,203,200_.jpg"),
        Book(R.string.are_you_my_mother, R.string.dr_seuss, R.drawable.areyoumymother,
        false, "https://cv01.twirpx.net/1746/1746752.jpg?t=20190715045620"),
        Book(R.string.where_is_babys_belly_button, R.string.karen_katz,
            R.drawable.whereisbabysbellybutton,
        false, "https://images-na.ssl-images-amazon.com/images/I/71QmQucSriL.jpg"),
        Book(R.string.on_the_night_you_were_born, R.string.nancy_tillman, R.drawable.onthenightyouwereborn,
        false, "https://images-na.ssl-images-amazon.com/images/I/71EAPz3rdCL.jpg"),
        Book(R.string.hand_hand_fingers_thumb, R.string.dr_seuss, R.drawable.handhandfingersthumb,
        false, "https://images-na.ssl-images-amazon.com/images/I/817u4Bp0vjL.jpg"),
        Book(R.string.the_very_hungry_caterpillar, R.string.eric_carle, R.drawable.theveryhungrycaterpillar,
        false, "https://www.podpisnie.ru/upload/resize_images/13416623/classic_312x460_13416623.jpeg"),
        Book(R.string.the_going_to_bed_book, R.string.sandra_boynton, R.drawable.thegoingtobedbook,
        false, "https://images-na.ssl-images-amazon.com/images/I/71slsnNNChL.jpg"),
        Book(R.string.oh_baby_go_baby, R.string.dr_seuss, R.drawable.ohbabygobaby,
        false, "https://images-na.ssl-images-amazon.com/images/I/51IPTUQXhoL._SX258_BO1,204,203,200_.jpg"),
        Book(R.string.the_tooth_book, R.string.dr_seuss, R.drawable.thetoothbook,
        false, "https://s1.livelib.ru/boocover/1001781476/o/bc8d/Dr._Seuss__The_Tooth_Book.jpeg"),
        Book(R.string.one_fish_two_fish_red_fish_blue_fish, R.string.dr_seuss, R.drawable.onefish,
        false, "https://s1.livelib.ru/boocover/1000569819/o/b479/Dr._Seuss__One_Fish_Two_Fish_Red_Fish_Blue_Fish.jpeg"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView: GridView = findViewById(R.id.gridview)
        val booksAdapter = BooksAdapter(this, bookArray)
        gridView.onItemClickListener = AdapterView.OnItemClickListener {
            p0, p1, p2, p3 ->
            val book = booksAdapter.getItem(p2) as Book
            book.toogleFavourite()
            booksAdapter.notifyDataSetChanged()
        }

        gridView.adapter = booksAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val favouriteBooksNames = arrayListOf<Int>()
        for (book in bookArray) {
            if (book.isFavourite) {
                favouriteBooksNames.add(book.name)
            }
        }
        outState.putIntegerArrayList(FAVOURITE_BOOKS_NAMES_KEY, favouriteBooksNames)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val favouriteBooksNames = savedInstanceState.getIntegerArrayList(FAVOURITE_BOOKS_NAMES_KEY)

        for (favouriteBookName in favouriteBooksNames!!) {
            for (book in bookArray) {
                if (book.name == favouriteBookName) {
                    book.isFavourite = true
                    break
                }
            }
        }
    }
}