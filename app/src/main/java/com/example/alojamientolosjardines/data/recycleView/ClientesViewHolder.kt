package com.example.alojamientolosjardines.data.recycleView

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.alojamientolosjardines.data.model.ClienteModel
import com.example.alojamientolosjardines.databinding.ItemClientesBinding

class ClientesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemClientesBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(clientModel: ClienteModel, onClickListener:(Array<String>) -> Unit) {
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
            clientModel.id
        )

        binding.editHabitacion.setOnClickListener {
            clientChange[0] = "C"
            onClickListener(clientChange)
        }

        binding.editFecha.setOnClickListener {
            clientChange[1] = "C"
            onClickListener(clientChange)
        }

        binding.editHora.setOnClickListener {
            clientChange[2] = "C"
            onClickListener(clientChange)
        }

        binding.editApellidos.setOnClickListener {
            clientChange[3] = "C"
            onClickListener(clientChange)
        }

        binding.editDni.setOnClickListener {
            clientChange[4] = "C"
            onClickListener(clientChange)
        }

        binding.editPrecio.setOnClickListener {
            clientChange[5] = "C"
            onClickListener(clientChange)
        }

        binding.editProcedencia.setOnClickListener {
            clientChange[6] = "C"
            onClickListener(clientChange)
        }

        binding.editObservacion.setOnClickListener {
            clientChange[7] = "C"
            onClickListener(clientChange)
        }
    }
}