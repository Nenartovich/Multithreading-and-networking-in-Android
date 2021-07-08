package com.nenartovich.task1_1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var menuItemClickListener: MenuItemClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myFragment = RecyclerFragment.newInstance()
        menuItemClickListener = myFragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_main_container, myFragment, null)
            .commit()

        val toolbar: Toolbar = findViewById(R.id.tb_toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_add_text -> {
                menuItemClickListener.addTextItem()
            }

            R.id.btn_add_image -> {
                menuItemClickListener.addImageItem()
            }
        }
        return true
    }

    interface MenuItemClickListener {
        fun addTextItem()
        fun addImageItem()
    }
}