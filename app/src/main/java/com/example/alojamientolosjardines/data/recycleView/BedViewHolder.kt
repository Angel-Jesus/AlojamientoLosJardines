package com.example.alojamientolosjardines.data.recycleView

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.data.model.RoomModel
import com.example.alojamientolosjardines.databinding.RecycleRoomBinding
import kotlin.coroutines.coroutineContext

class BedViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = RecycleRoomBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(context: Context, roomModel: RoomModel, position: Int, onClickListener:(Int) -> Unit) {
        binding.txtBed.text = "Habitacion: ${roomModel.roomNumber}"
        binding.txtCost.text = "Precio: ${roomModel.roomCost}"
        binding.txtState.text = roomModel.roomState

        if(roomModel.roomState == "DISPONIBLE")
        {
            binding.btnState.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.bed_green)
        }
        else
        {
            binding.btnState.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.bed_red)
        }

        binding.layoutBed.setOnClickListener {
            onClickListener(position)
        }
    }

}