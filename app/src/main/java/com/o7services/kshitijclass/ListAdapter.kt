package com.o7services.kshitijclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(var list: List<StudentTable>) : BaseAdapter() {
    override fun getCount(): Int {
        return  list.size
    }

    override fun getItem(position: Int): Any {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return list.size.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    var initView = LayoutInflater.from(parent?.context).inflate(R.layout.layout_item, parent, false)
        var id = initView.findViewById<TextView>(R.id.tvId)
        var name = initView.findViewById<TextView>(R.id.tvName)
        id.setText(list[position].id.toString())
        name.setText(list[position].name)
        return initView
    }
}