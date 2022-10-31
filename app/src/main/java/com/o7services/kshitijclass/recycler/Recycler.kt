package com.o7services.kshitijclass.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.o7services.kshitijclass.R
import com.o7services.kshitijclass.StudentTable

class Recycler(var list: MutableList<StudentTable>) : RecyclerView.Adapter<Recycler.RecyclerViewHolder>() {

    class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){
       var tvId = view.findViewById<TextView>(R.id.tvId)
       var tvName = view.findViewById<TextView>(R.id.tvName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.tvId.setText(list[position].id.toString())
            holder.tvName.setText(list[position].name)
    }

    override fun getItemCount(): Int {
        return list.size
        }
}