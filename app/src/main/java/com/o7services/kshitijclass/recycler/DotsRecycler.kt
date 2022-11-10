package com.o7services.kshitijclass.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.o7services.kshitijclass.R
import com.o7services.kshitijclass.StudentTable

class DotsRecycler(var context: Context, var currentItem: Int) : RecyclerView.Adapter<DotsRecycler.RecyclerViewHolder>() {

    class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){
       var tvId = view.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_dots, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        if(position == currentItem){
            holder.tvId.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        }else{
            //same color
        }

    }

    override fun getItemCount(): Int {
        return 6
        }

    fun updatePosition( position: Int){
        currentItem = position
        notifyDataSetChanged()
    }
}