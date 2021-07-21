package com.nenartovich.example08_runtimepermissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val WRITE_PERMISSION_RC = 123
    }

    private lateinit var mInput: EditText
    private lateinit var mWrite: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mInput = findViewById(R.id.et_input)
        mWrite = findViewById(R.id.btn_write)

        mWrite.setOnClickListener {
            val textToWrite = mInput.text.toString()
            writeToFileIfNotEmpty(textToWrite)
        }
    }

    private fun writeToFileIfNotEmpty(textToWrite: String) {
        if (TextUtils.isEmpty(textToWrite)) {
            Toast.makeText(this, "text is empty", Toast.LENGTH_SHORT).show()
        } else {
            writeToFileWithPermissionRequestIfNeed(textToWrite)
        }
    }

    private fun writeToFileWithPermissionRequestIfNeed(textToWrite: String) {
        if (isWritePermissionGranted()) {
            writeToFile(textToWrite)
        } else {
            requestWritePermission()
        }
    }

    private fun requestWritePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            AlertDialog.Builder(this)
                .setMessage("Permission to write data to file")
                .setPositiveButton("Allow"
                ) { p0, p1 ->
                    run {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            WRITE_PERMISSION_RC
                        )
                    }
                }
                .show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_PERMISSION_RC)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != WRITE_PERMISSION_RC) return
        if (grantResults.size != 1) return
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val textToWrite = mInput.text.toString()
            writeToFile(textToWrite)
        } else {
            AlertDialog.Builder(this)
                .setMessage("You can allow permission in the settings")
                .setPositiveButton("Ok",null)
                .show()
        }
    }

    private fun writeToFile(textToWrite: String) {
        Toast.makeText(this, "$textToWrite is written text", Toast.LENGTH_SHORT).show()
    }

    private fun isWritePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }
}