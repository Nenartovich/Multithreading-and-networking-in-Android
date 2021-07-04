package com.nenartovich.example06_recyclerview

import android.database.Cursor
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.example06_recyclerview.mock.Mock
import com.nenartovich.example06_recyclerview.mock.MockHolder

class ContactsAdapter : RecyclerView.Adapter<MockHolder>() {

    private var cursor: Cursor? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MockHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.ll_mock, parent, false)
        return MockHolder(view)
    }

    override fun onBindViewHolder(holder: MockHolder, position: Int) {
        if (this.cursor!!.moveToPosition(position)) {
            val name = this.cursor!!.getString(this.cursor!!.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val id = this.cursor!!.getInt(this.cursor!!.getColumnIndex(ContactsContract.Contacts._ID))
            holder.bind(Mock(name, id))
        }
    }

    override fun getItemCount(): Int {
        return if (cursor != null) cursor!!.count else 0
    }

    fun swapCursor(cursor: Cursor?) {
        if (cursor != null && cursor != this.cursor) {
            if (this.cursor != null) {
                this.cursor!!.close()
            }
            this.cursor = cursor
            notifyDataSetChanged()
        }
    }
}