package com.nenartovich.example06_recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class RecyclerFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var recycler: RecyclerView
    private val contactsAdapter: ContactsAdapter = ContactsAdapter()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var errorView: View
    private var listener: ContactsAdapter.OnItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ContactsAdapter.OnItemClickListener) {
            listener = context as ContactsAdapter.OnItemClickListener
        }
    }

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
        recycler.adapter = contactsAdapter
        contactsAdapter.setListener(listener)
    }
    
    companion object {
        fun newInstance(): RecyclerFragment = RecyclerFragment()
    }

    override fun onRefresh() {
        loaderManager.restartLoader(0, null, this)
    }



    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            activity!!,
            ContactsContract.Contacts.CONTENT_URI,
            arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME),
            null,
            null,
            ContactsContract.Contacts._ID
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        contactsAdapter.swapCursor(data)
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }
}