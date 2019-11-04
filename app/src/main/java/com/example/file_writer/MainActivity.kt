package com.example.file_writer

import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    val FILE_PATH_WITOUHT_PERMISSION by lazy {
        getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
    }
    val FILE_PATH_WITH_PERMISSION by lazy {
        Environment.getExternalStorageDirectory()
    }
    val FILE_NAME = "text.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        b_clear.setOnClickListener { clearEditText() }
        b_write.setOnClickListener { writeWithoutPermission() }
        b_read.setOnClickListener { readWithoutPermission() }
        b_write_with_permission.setOnClickListener { writeWithPermission() }
        b_read_with_permission.setOnClickListener { readWithPermission() }
    }

    private fun clearEditText() {
        et.text.clear();
    }

    private fun readWithPermission() {
        try {
            et.setText(File(FILE_PATH_WITH_PERMISSION, FILE_NAME).readText())
        } catch (e: Exception) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
        }
    }

    private fun writeWithPermission() {
        try {
            File(FILE_PATH_WITH_PERMISSION, FILE_NAME).writeText(et.text.toString())
            clearEditText()
        } catch (e: Exception) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
        }
    }

    private fun readWithoutPermission() {
        et.setText(File(FILE_PATH_WITOUHT_PERMISSION, FILE_NAME).readText())
    }

    private fun writeWithoutPermission() {
        File(FILE_PATH_WITOUHT_PERMISSION, FILE_NAME).writeText(et.text.toString())
        clearEditText()
    }
}
