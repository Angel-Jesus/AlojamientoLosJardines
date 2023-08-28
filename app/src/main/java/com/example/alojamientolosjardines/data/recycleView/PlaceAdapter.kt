package com.example.alojamientolosjardines.data.recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.data.model.PlaceModel

class PlaceAdapter(private val placeList: ArrayList<PlaceModel>): RecyclerView.Adapter<PlaceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlaceViewHolder(layoutInflater.inflate(R.layout.item_place,parent,false))
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val item = placeList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = placeList.size
}