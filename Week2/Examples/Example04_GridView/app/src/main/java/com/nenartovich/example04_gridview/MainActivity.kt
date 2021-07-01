package com.nenartovich.example04_gridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val gridView: GridView = findViewById(R.id.gridview)
        val booksAdapter = BooksAdapter(this, getBooks())
        gridView.onItemClickListener = AdapterView.OnItemClickListener {
            p0, p1, p2, p3 ->
            val book = booksAdapter.getItem(p2) as Book
            book.toogleFavourite()
            booksAdapter.notifyDataSetChanged()
        }

        gridView.adapter = booksAdapter


    }


    private fun getBooks() : Array<Book>  = arrayOf(
        Book(R.string.abc_an_amazing_alphabet_book, R.string.dr_seuss, R.drawable.abc,
            false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/abc.jpg"),
        Book(R.string.are_you_my_mother, R.string.dr_seuss, R.drawable.areyoumymother,
             false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/areyoumymother.jpg"),
        Book(R.string.where_is_babys_belly_button, R.string.karen_katz, R.drawable.whereisbabysbellybutton,
             false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/whereisbabysbellybutton.jpg"),
        Book(R.string.on_the_night_you_were_born, R.string.nancy_tillman, R.drawable.onthenightyouwereborn,
            false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/onthenightyouwereborn.jpg"),
        Book(R.string.hand_hand_fingers_thumb, R.string.dr_seuss, R.drawable.handhandfingersthumb,
            false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/handhandfingersthumb.jpg"),
        Book(R.string.the_very_hungry_caterpillar, R.string.eric_carle, R.drawable.theveryhungrycaterpillar,
            false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/theveryhungrycaterpillar.jpg"),
        Book(R.string.the_going_to_bed_book, R.string.sandra_boynton, R.drawable.thegoingtobedbook,
            false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/thegoingtobedbook.jpg"),
        Book(R.string.oh_baby_go_baby, R.string.dr_seuss, R.drawable.ohbabygobaby,
            false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/ohbabygobaby.jpg"),
        Book(R.string.the_tooth_book, R.string.dr_seuss, R.drawable.thetoothbook,
            false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/thetoothbook.jpg"),
        Book(R.string.one_fish_two_fish_red_fish_blue_fish, R.string.dr_seuss, R.drawable.onefish,
            false, "http://www.raywenderlich.com/wp-content/uploads/2016/03/onefish.jpg"))

}