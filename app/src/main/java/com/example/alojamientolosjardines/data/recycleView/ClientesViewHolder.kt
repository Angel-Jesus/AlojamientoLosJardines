package com.example.alojamientolosjardines.data.recycleView

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.alojamientolosjardines.data.model.ClienteModel
import com.example.alojamientolosjardines.databinding.ItemClientesBinding

class ClientesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemClientesBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(clientModel: ClienteModel, onClickListener:(Array<String>, Int) -> Unit) {
        binding.editHabitacion.text = clientModel.habitacion
        binding.editFecha.text = clientModel.fecha
        binding.editHora.text = clientModel.hora
        binding.editApellidos.text = clientModel.apellido
        binding.editDni.text = clientModel.dni
        binding.editProcedencia.text = clientModel.procedencia
        binding.editPrecio.text = "S/. ${clientModel.precio}"
        binding.editObservacion.text = clientModel.observaciones

        val clientChange = arrayOf(
            clientModel.habitacion,
            clientModel.fecha,
            clientModel.hora,
            clientModel.apellido,
            clientModel.dni,
            clientModel.precio,
            clientModel.procedencia,
            clientModel.observaciones,
            "UPDATE",
            clientModel.id
        )

        binding.editHabitacion.setOnClickListener {
            onClickListener(clientChange, 0)
        }

        binding.editFecha.setOnClickListener {
            onClickListener(clientChange, 1)
        }

        binding.editHora.setOnClickListener {
            onClickListener(clientChange, 2)
        }

        binding.editApellidos.setOnClickListener {
            onClickListener(clientChange, 3)
        }

        binding.editDni.setOnClickListener {
            onClickListener(clientChange, 4)
        }

        binding.editPrecio.setOnClickListener {
            onClickListener(clientChange, 5)
        }

        binding.editProcedencia.setOnClickListener {
            onClickListener(clientChange, 6)
        }

        binding.editObservacion.setOnClickListener {
            onClickListener(clientChange, 7)
        }
    }
}