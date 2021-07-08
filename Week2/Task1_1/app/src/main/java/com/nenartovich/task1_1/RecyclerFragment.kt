package com.nenartovich.task1_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.task1_1.items.ImageItem
import com.nenartovich.task1_1.items.TextItem

class RecyclerFragment : Fragment() {
    val data = arrayOf<Any>(
        TextItem("Jack Smith", "Android dev."),
        ImageItem(R.drawable.picture1),
        TextItem("Lil Peep", "Rockstar"),
        ImageItem(R.drawable.picture2),
        ImageItem(R.drawable.picture3),
        ImageItem(R.drawable.picture4),
        ImageItem(R.drawable.picture5),
        TextItem("Eugene Nenartovich", "Lorem ipsum dolor"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = view.findViewById(R.id.rv_recycler)
        val adapter = CustomAdapter(data)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }
}