package com.dfl.todolistapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView


class ToDoListAdapter(var listToDo: ArrayList<String>, var contextContent: Context?) :
    BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(contextContent)
                .inflate(R.layout.layout_list_notes, parent, false)
        }

        val currentItem = getItem(position)

        val textViewItemName = view!!.findViewById(R.id.elementTextView) as TextView
        val deleteButton = view.findViewById(R.id.deleteTextButton) as ImageButton
        deleteButton.setOnClickListener {
            (contextContent as MainActivity).deleteItem(it.tag as Int)
        }
        deleteButton.tag = position
        textViewItemName.text = currentItem

        return view
    }

    override fun getItem(position: Int): String {
        return listToDo[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listToDo.size
    }
}