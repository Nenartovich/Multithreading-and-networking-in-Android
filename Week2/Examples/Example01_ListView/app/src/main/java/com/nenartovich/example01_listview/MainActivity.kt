package com.nenartovich.example01_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var mListView: ListView
    private val mAdapter = ListViewAdapter(listOf(
        ListObject(R.drawable.ic_android_black_24dp, "Black android"),
        ListObject(R.drawable.ic_baseline_access_time_24, "Time"),
        ListObject(R.drawable.ic_baseline_architecture_24, "Architecture icon"),
        ListObject(R.drawable.ic_baseline_bolt_24, "Bolt icon"),
        ListObject(R.drawable.ic_baseline_cloud_queue_24, "Cloud"),
        ListObject(R.drawable.ic_baseline_create_24, "Pencil")))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mListView = findViewById(R.id.listView)
        mListView.adapter = mAdapter
    }
}