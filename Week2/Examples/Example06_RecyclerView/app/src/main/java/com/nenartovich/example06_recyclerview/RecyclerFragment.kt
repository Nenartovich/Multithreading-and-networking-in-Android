package com.nenartovich.example06_recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.example06_recyclerview.mock.MockAdapter

class RecyclerFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private val mockAdapter: MockAdapter = MockAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_recycler, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.recycler)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mockAdapter
    }
    
    companion object {
        fun newInstance(): RecyclerFragment = RecyclerFragment()
    }
}