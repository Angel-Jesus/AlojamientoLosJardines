package com.example.alojamientolosjardines.data.recycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.data.model.RoomModel

class BedAdapter(private val context: Context, private val list_room:ArrayList<RoomModel>, private val onClickListener:(Int) -> Unit): RecyclerView.Adapter<BedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BedViewHolder(layoutInflater.inflate(R.layout.recycle_room,parent,false))
    }

    override fun onBindViewHolder(holder: BedViewHolder, position: Int) {
        val item = list_room[position]
        holder.render(context, item, position, onClickListener)
    }

    override fun getItemCount(): Int = list_room.size
}