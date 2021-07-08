package com.nenartovich.task1_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.task1_1.data.Resources
import com.nenartovich.task1_1.items.ImageItem
import com.nenartovich.task1_1.items.TextItem

class RecyclerFragment : Fragment(), MainActivity.MenuItemClickListener {
    private val data = mutableListOf<Any>()

    private val imageResources = Resources.imageResources
    private val titles = Resources.titles
    private val descriptions = Resources.descriptions
    private val adapter = CustomAdapter(data)
    private var textCount = 0
    private var imageCount = 0

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
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }

    override fun addTextItem() {
        data.add(TextItem(titles[textCount % titles.size]!!,
            descriptions[textCount % descriptions.size]!!))
        textCount++
        adapter.notifyDataSetChanged()
    }

    override fun addImageItem() {
        data.add(ImageItem(imageResources[imageCount % imageResources.size]!!))
        imageCount++
        adapter.notifyDataSetChanged()
    }
}