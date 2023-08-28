package com.example.alojamientolosjardines.iu.view.dialogFragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.data.model.ClienteModel
import com.example.alojamientolosjardines.iu.viewmodel.ClienteViewModel
import com.google.android.material.textfield.TextInputEditText

class DialogFragmentOption(private var dataClient: Array<String>, private val indice: Int, private val client:ClienteViewModel): DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        return activity?.let {
            val alertDialog = AlertDialog.Builder(it)
            alertDialog.setView(requireActivity().layoutInflater.inflate(R.layout.dialog_option,null))
            alertDialog.setPositiveButton("Actualizar Registro") { btn, _ ->
                val txt = dialog?.findViewById<TextInputEditText>(R.id.edit_cambio)
                val cambio = txt?.text.toString()
                dataClient[indice] = cambio
                /*
                for(i in dataClient.indices){
                    print("${dataClient[i]}")
                    println()
                }
                println()
                */
                client.isClient.postValue(dataClient)
                btn.cancel()
            }
            alertDialog.setNegativeButton("Eliminar Registro"){btn,_ ->
                val deleteData = arrayOf(" ", " ", " ", " ", " ", " ", " ", " ","DELETE", dataClient[9])
                client.isClient.postValue(deleteData)
                btn.cancel()
            }
            alertDialog.create()
        }?:throw IllegalStateException("Activity is null!")
    }
}