package com.example.alojamientolosjardines.data.recycleView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.alojamientolosjardines.data.model.PlaceModel
import com.example.alojamientolosjardines.databinding.ItemPlaceBinding

class PlaceViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemPlaceBinding.bind(view)
    fun render(Lugar: PlaceModel){
        binding.idRegion.text = Lugar.procedencia
        binding.idCantidadRegion.text = Lugar.cantidad.toString()
    }
}