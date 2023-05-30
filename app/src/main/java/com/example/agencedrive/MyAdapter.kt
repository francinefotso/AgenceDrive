package com.example.agencedrive

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private val datalist: List<News>, private val context: Context) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item, parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvHiading.text = datalist!!.get(position).heading
        holder.hours.text = datalist!!.get(position).hours

//        Log.i("{i}","${holder.tvHiading}")
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hours: TextView = itemView.findViewById(R.id.tvhours)
        val tvHiading: TextView = itemView.findViewById(R.id.tvHeading)
    }

}
private fun ShapeableImageView.setImageResource(titleImag: String) {

}