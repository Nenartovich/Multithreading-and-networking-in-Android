package com.nenartovich.task2

import android.app.DownloadManager
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etLinkField: EditText
    private lateinit var btnDownloadImage: Button
    private lateinit var ivImage: ImageView
    private lateinit var btnShowImage: Button
    private lateinit var mDownloadManager: DownloadManager
    private val list: ArrayList<Long> = ArrayList()
    private lateinit var onCompleteDownloading: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        mDownloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }


    private fun initUI() {
        etLinkField = findViewById(R.id.et_link)
        btnDownloadImage = findViewById(R.id.btn_download)
        ivImage = findViewById(R.id.iv_image)
        btnShowImage = findViewById(R.id.btn_show_image)

        btnDownloadImage.setOnClickListener {
            val downloadUri = Uri.parse("http://www.gadgetsaint.com/wp-content/uploads/2016/11/cropped-web_hi_res_512.png")
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            request.setAllowedOverRoaming(false)
            request.setTitle("Sample")
            request.setDescription("Downloading Sample.png")
            request.setVisibleInDownloadsUi(true)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Sample.png")
            val refId = mDownloadManager.enqueue(request)
            list.add(refId)
            onCompleteDownloading = object : BroadcastReceiver() {
                override fun onReceive(ctxt: Context?, intent: Intent?) {
                val referenceId = intent!!.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                list.remove(referenceId)
                if (list.isEmpty()) {
                    Log.e("INSIDE", " $referenceId")
                    val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this@MainActivity)
                        .setSmallIcon(R.drawable.ic_baseline_image_24)
                        .setContentTitle("Title")
                        .setContentText("Downloading completed")

                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.notify(455, mBuilder.build())
                }
            }
        }
        registerReceiver(onCompleteDownloading, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

            Toast.makeText(this, "$refId", Toast.LENGTH_SHORT).show()
            btnShowImage.isEnabled = true
        }

        btnShowImage.setOnClickListener {
            Toast.makeText(this, "Show", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(onCompleteDownloading)
    }
}