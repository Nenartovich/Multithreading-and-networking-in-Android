package com.nenartovich.task3

import android.content.AsyncTaskLoader
import android.content.Context
import java.util.concurrent.TimeUnit

class MyAsyncTaskLoader(context: Context) : AsyncTaskLoader<String>(context) {
    override fun loadInBackground(): String {
        try {
            TimeUnit.MILLISECONDS.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return "Completed!"
    }

    override fun onStartLoading() {
        forceLoad()
    }
}