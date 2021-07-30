package com.nenartovich.example09_writeintoexternalstorage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val FILENAME = "myfile"
    }

    private lateinit var mInput: EditText
    private lateinit var mFromInternal: TextView
    private lateinit var mFromExternal: TextView
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mIsExternalSwitch: Switch
    private lateinit var mDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        mIsExternalSwitch.isEnabled = isExternalStorageAvailable()

        mInput.setOnEditorActionListener {
                v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val text = v.text.toString()
                saveToFile(text, mIsExternalSwitch.isChecked)
                updateTextViews()
            }
            false
        }

        mDelete.setOnClickListener {
            deleteFile(mIsExternalSwitch.isChecked)
        }

        updateTextViews()
    }

    private fun deleteFile(isExternal: Boolean) {
        if (isExternal) {
            val file = File(Environment.getExternalStorageDirectory().absolutePath, FILENAME)
            if (file.delete()) {
                Toast.makeText(this, "Deleted from external", Toast.LENGTH_SHORT).show()
            }
        } else {
            deleteFile(FILENAME)
            Toast.makeText(this, "Deleted from internal", Toast.LENGTH_SHORT).show()
        }
        updateTextViews()
    }

    private fun updateTextViews() {
        mFromInternal.text = readFromInternalFileIfOption()
        mFromExternal.text = readFromExternalFileIfOption()
    }

    private fun readFromExternalFileIfOption(): String {
        val file = File(
            Environment.getExternalStorageDirectory().absolutePath,
            FILENAME
        )
        try {
            BufferedReader(InputStreamReader(FileInputStream(file))).use { reader ->
                val stringBuilder = StringBuilder()
                var string: String?
                while (reader.readLine().also { string = it } != null) {
                    stringBuilder.append(string).append("\n")
                }
                return stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Can't read from file", Toast.LENGTH_SHORT).show()
        }
        return ""
    }

    private fun readFromInternalFileIfOption(): String {
        try {
            BufferedReader(InputStreamReader(openFileInput(FILENAME))).use { reader ->
                val stringBuilder = StringBuilder()
                var string: String?
                while (reader.readLine().also { string = it } != null) {
                    stringBuilder.append(string).append("\n")
                }
                return stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Can't read from file", Toast.LENGTH_SHORT).show()
        }
        return ""
    }

    private fun saveToFile(text: String, isInExternal: Boolean) {
        if (isInExternal) {
            saveToExternalFile(text)
        } else {
            saveToInternalFile(text)
        }
    }

    private fun saveToInternalFile(text: String) {
        try {
            val textToWrite = text + '\n'
            val outputStream = openFileOutput(FILENAME, Context.MODE_APPEND)
            outputStream.write(textToWrite.toByteArray())
            outputStream.close()
            Toast.makeText(this, "Written to internal", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveToExternalFile(text: String) {
        try {
            val textToWrite = text + '\n'
            val file = File(Environment.getExternalStorageDirectory(), FILENAME)
            val outputStream = FileOutputStream(file, true)
            outputStream.write(textToWrite.toByteArray())
            outputStream.close()
            Toast.makeText(this, "Written to external", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun isExternalStorageAvailable(): Boolean {
        val state = Environment.getExternalStorageState()
        return state == Environment.MEDIA_MOUNTED
    }

    private fun initUI() {
        mFromInternal = findViewById(R.id.tv_from_internal_file)
        mFromExternal = findViewById(R.id.tv_from_external_file)
        mIsExternalSwitch = findViewById(R.id.switch_is_external)
        mInput = findViewById(R.id.et_some_text)
        mDelete = findViewById(R.id.btn_delete_file)
    }
}