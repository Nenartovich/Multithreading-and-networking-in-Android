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

    private val adapter = CustomAdapter(data)

    private lateinit var recycler: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rv_recycler)
        layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
    }

    companion object {
        private val imageResources = Resources.imageResources
        private val titles = Resources.titles
        private val descriptions = Resources.descriptions

        private val data = mutableListOf(TextItem(titles[0]!!, descriptions[0]!!),ImageItem(imageResources[0]!!))
        private var textCount = 1
        private var imageCount = 1

        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }

    override fun addTextItem() {
        data.add(TextItem(titles[textCount % titles.size]!!,
            descriptions[textCount % descriptions.size]!!))
        textCount++
        recycler.scrollToPosition(data.size - 1)
        adapter.notifyDataSetChanged()
    }

    override fun addImageItem() {
        data.add(ImageItem(imageResources[imageCount % imageResources.size]!!))
        imageCount++
        recycler.scrollToPosition(data.size - 1)
        adapter.notifyDataSetChanged()
    }
}