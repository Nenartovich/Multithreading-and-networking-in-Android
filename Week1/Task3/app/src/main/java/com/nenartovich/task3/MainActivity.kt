package com.nenartovich.task3

import android.annotation.SuppressLint
import android.app.LoaderManager
import android.content.Loader
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.AsyncTaskLoader

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    private val MY_ASYNC_TASK_ID = 1

    private lateinit var startButton: Button
    private lateinit var textView: TextView
    private lateinit var progress: ProgressBar

    private var mLoaderManager: LoaderManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.btnStartButton)
        textView = findViewById(R.id.tvText)
        progress = findViewById(R.id.pbProgress)

        mLoaderManager = loaderManager
        if (mLoaderManager!!.getLoader<String>(MY_ASYNC_TASK_ID) != null) {
            updateUI()
            mLoaderManager?.initLoader(MY_ASYNC_TASK_ID, null, this)
        }
    }

    private fun updateUI() {
        progress.visibility = ProgressBar.VISIBLE
        textView.text = resources.getString(R.string.loading)
        startButton.isEnabled = false
    }

    fun startMyAsyncTask(view: View) {
        updateUI()
        mLoaderManager?.initLoader(MY_ASYNC_TASK_ID, null, this)
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<String> {
        return MyAsyncTaskLoader(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onLoadFinished(p0: Loader<String>?, p1: String?) {
        progress.visibility = ProgressBar.GONE
        textView.text = "Ready"
        startButton.isEnabled = true
    }

    override fun onLoaderReset(p0: Loader<String>?) {
    }
}