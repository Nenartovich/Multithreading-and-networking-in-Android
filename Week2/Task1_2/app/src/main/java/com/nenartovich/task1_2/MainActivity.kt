package com.nenartovich.task1_2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nenartovich.task1_2.mock.MockAdapter

class MainActivity : AppCompatActivity(), ContactsAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, RecyclerFragment.newInstance())
                .commit()
        }
    }

    override fun onItemClick(id: Int) {

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                    ContactsContract.CommonDataKinds.Phone.TYPE + " = ?",
            arrayOf(id.toString(), ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE.toString()),
            null
        )

        if (cursor != null && cursor.moveToFirst()) {
            val number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            cursor.close()
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + number)
            startActivity(intent)
        }
        Toast.makeText(this, "Click $id", Toast.LENGTH_SHORT).show()
    }
}