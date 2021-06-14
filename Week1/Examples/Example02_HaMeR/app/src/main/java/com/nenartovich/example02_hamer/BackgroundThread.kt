package com.nenartovich.example02_hamer

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message

class BackgroundThread(name: String) : HandlerThread(name) {
    private val MESSAGE_ID = 0
    private val PERCENT = 100
    private val PARTS_COUNT = 50
    private val PART_SIZE = PERCENT / PARTS_COUNT

    private lateinit var mainThreadHandler: Handler
    private lateinit var backgroundThreadHandler: Handler
    private lateinit var callback: Callback

    fun registerCallback(callback: Callback) {
        this.callback = callback
    }

    @SuppressLint("HandlerLeak")
    override fun onLooperPrepared() {
        mainThreadHandler = Handler(Looper.getMainLooper())

        backgroundThreadHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    MESSAGE_ID -> {
                        val bitmap = msg.obj as Bitmap
                        processBitmap(bitmap)
                    }
                    else -> throw Exception("Lalalaaa")
                }

            }
        }

    }


    fun processBitmap(bitmap: Bitmap) {
        val h = bitmap.height
        val w = bitmap.width
        val pixels = IntArray(h*w) { 0 }
        bitmap.getPixels(pixels, 0, w, 0, 0, w, h)

        val progress = IntArray(1) { 0 }

        for (i in 0 until w*h) {
            val hex = String.format("#%06X",  (0xFFFFFF and pixels[i]))
            val red = hex.substring(1, 3)
            val green = hex.substring(3, 5)
            val blue = hex.substring(5)
            pixels[i] = Integer.parseInt(green + blue + red , 16)

            val part = w * h / PARTS_COUNT
            if (i % part == 0) {
                progress[0] = i / part * PART_SIZE
                mainThreadHandler.post {
                    callback.updateProgress(progress[0])
                }
            }
        }

        val result = Bitmap.createBitmap(pixels, w, h, Bitmap.Config.RGB_565)
        mainThreadHandler.post {
            callback.processingDone(result)
        }
    }

    fun performOperation(bitmap: Bitmap) {
        backgroundThreadHandler.obtainMessage(MESSAGE_ID, bitmap).sendToTarget()
    }

    interface Callback {
        fun updateProgress(currentProgress: Int)
        fun processingDone(bitmap: Bitmap)
    }
}