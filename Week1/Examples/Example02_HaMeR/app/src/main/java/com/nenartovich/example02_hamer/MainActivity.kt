package com.nenartovich.example02_hamer

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.graphics.drawable.toBitmap


class MainActivity : AppCompatActivity(), BackgroundThread.Callback {

    private lateinit var backgroundThread: BackgroundThread
    private lateinit var imageView: ImageView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStartProcess = findViewById<Button>(R.id.btn_perform)
        btnStartProcess.setOnClickListener { view -> onButtonClick(view) }
        imageView = findViewById(R.id.iv_doge)
        progressBar = findViewById(R.id.progress)
        backgroundThread = BackgroundThread("MyBackgroundThread")
        backgroundThread.start()
        backgroundThread.looper
        backgroundThread.registerCallback(this)
    }

    private fun onButtonClick(view: View) {
        val drawable = imageView.drawable
        backgroundThread.performOperation(drawable.toBitmap())
    }

    override fun updateProgress(currentProgress: Int) {
        progressBar.progress = currentProgress
    }

    override fun processingDone(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }

    override fun onDestroy() {
        backgroundThread.quit()
        super.onDestroy()
    }
}