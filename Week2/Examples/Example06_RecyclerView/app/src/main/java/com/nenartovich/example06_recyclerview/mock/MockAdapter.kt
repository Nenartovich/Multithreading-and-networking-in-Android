package com.nenartovich.example06_recyclerview.mock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.example06_recyclerview.R

class MockAdapter : RecyclerView.Adapter<MockHolder>() {
    companion object {
        private val data: ArrayList<Mock> = MockGenerator.generate(5) as ArrayList<Mock>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MockHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.ll_mock, parent, false)
        return MockHolder(view)
    }

    override fun onBindViewHolder(holder: MockHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun addData(mocks: List<Mock>, refresh: Boolean) {
        if (refresh) {
            data.clear()
        }
        data.addAll(mocks)
        notifyDataSetChanged()
    }
}