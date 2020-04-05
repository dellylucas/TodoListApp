package com.dfl.todolistapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintStream
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val listElements = ArrayList<String>()
    private var adapter: ToDoListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInformation()
        adapter = ToDoListAdapter(listElements, this)
        listToDo.adapter = adapter
        addTextButton.setOnClickListener {
            addItem()
        }
    }

    private fun getInformation() {
        try {
            val scan = Scanner(openFileInput(getString(R.string.file_name_list)))
            while (scan.hasNextLine()) {
                val line = scan.nextLine()
                listElements.add(line)
            }
            scan.close()
        } catch (ex: Exception) {
            Toast.makeText(this, getString(R.string.welcome_alert), Toast.LENGTH_SHORT).show()
        }
    }

    private fun addItem() {
        val text = textInsertEditText.text.toString()
        if (text.isNotBlank()) {
            val output =
                PrintStream(openFileOutput(getString(R.string.file_name_list), MODE_APPEND))
            output.appendln(text)
            listElements.add(text)
            output.close()

            textInsertEditText.text.clear()
            adapter?.notifyDataSetChanged()
        } else {
            Toast.makeText(this, getString(R.string.empty_input_alert), Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteItem(position: Int) {
        listElements.removeAt(position)
        val output = PrintStream(openFileOutput(getString(R.string.file_name_list), MODE_PRIVATE))
        listElements.map {
            output.appendln(it)
        }
        output.close()
        adapter?.notifyDataSetChanged()
    }
}
