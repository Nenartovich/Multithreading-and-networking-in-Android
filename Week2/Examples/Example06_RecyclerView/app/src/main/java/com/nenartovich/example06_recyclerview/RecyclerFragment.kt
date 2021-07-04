package com.nenartovich.example06_recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nenartovich.example06_recyclerview.mock.MockAdapter
import com.nenartovich.example06_recyclerview.mock.MockGenerator

class RecyclerFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var recycler: RecyclerView
    private val mockAdapter: MockAdapter = MockAdapter()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var errorView: View
    private val random: java.util.Random = java.util.Random()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_recycler, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.recycler)
        swipeRefreshLayout = view.findViewById(R.id.refresher)
        swipeRefreshLayout.setOnRefreshListener(this)
        errorView = view.findViewById(R.id.error_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mockAdapter
    }
    
    companion object {
        fun newInstance(): RecyclerFragment = RecyclerFragment()
    }

    override fun onRefresh() {
        swipeRefreshLayout.postDelayed({
            val count = random.nextInt(4)
            if (count == 0) {
                showError()
            } else {
                showData(count)
            }

            if (swipeRefreshLayout.isRefreshing) {
                swipeRefreshLayout.isRefreshing = false
            }
        }, 2000)
    }

    private fun showData(count: Int) {
        mockAdapter.addData(MockGenerator.generate(count), true)
        errorView.visibility = View.GONE
        recycler.visibility = View.VISIBLE
    }

    private fun showError() {
        errorView.visibility = View.VISIBLE
        recycler.visibility = View.GONE
    }
}