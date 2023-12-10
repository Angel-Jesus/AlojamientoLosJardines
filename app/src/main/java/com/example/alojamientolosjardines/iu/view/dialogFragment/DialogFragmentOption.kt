package com.example.alojamientolosjardines.iu.view.dialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.iu.viewmodel.ClienteViewModel
import com.example.alojamientolosjardines.iu.viewmodel.PickerViewModel
import com.google.android.material.textfield.TextInputEditText

class DialogFragmentOption(val activity: AppCompatActivity, private var dataClient: Array<String>, private val indice: Int, private val client:ClienteViewModel): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.dialog_option, container, false)

        val btnDelete = rootView.findViewById<Button>(R.id.btnDelete)
        val btnUpdate = rootView.findViewById<Button>(R.id.btnUpdate)
        val dataInput = rootView.findViewById<TextInputEditText>(R.id.edit_cambio)

        if(indice in 1..2)
        {
            dataInput.isFocusable = false
        }

        //Para datos de fecha o hora
        dataInput.setOnClickListener {
            if(indice in 1..2)
            {
                val picker: PickerViewModel by viewModels()

                if(indice == 1)
                {
                    picker.onDate(activity)
                    picker.date.observe(this) {
                        dataInput.setText(it)
                    }
                }
                else
                {
                    picker.onHour(activity)
                    picker.hour.observe(this) {
                        dataInput.setText(it)
                    }
                }

            }
        }

        btnUpdate.setOnClickListener {
            val txt = dialog?.findViewById<TextInputEditText>(R.id.edit_cambio)
            val cambio = txt?.text.toString()
            dataClient[indice] = cambio
            client.isClient.postValue(dataClient)
            dismiss()
        }

        btnDelete.setOnClickListener {
            val deleteData = arrayOf("'", "'", "'", "'", "'", "'", "'", "'", "DELETE", dataClient[9])
            client.isClient.postValue(deleteData)
            dismiss()
        }

        return rootView
    }
}