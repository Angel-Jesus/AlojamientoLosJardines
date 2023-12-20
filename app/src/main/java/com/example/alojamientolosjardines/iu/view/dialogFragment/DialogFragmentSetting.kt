package com.example.alojamientolosjardines.iu.view.dialogFragment

import android.app.*
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.di.DatabaseModule
import com.example.alojamientolosjardines.iu.viewmodel.RoomViewModel
import com.google.android.material.textfield.TextInputEditText

class DialogFragmentSetting : DialogFragment() {
    private val numberRoom = DatabaseModule.roomProvider.roomNumber
    private val priceModel : RoomViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val alertDialog = AlertDialog.Builder(it)
            alertDialog.setView(requireActivity().layoutInflater.inflate(R.layout.view_setting,null))
            alertDialog.setPositiveButton("Actualizar precio"){btn,_ ->
                val roomView = dialog?.findViewById<TextInputEditText>(R.id.edit_nhabitacion)
                val priceView = dialog?.findViewById<TextInputEditText>(R.id.edit_preciochange)

                val room = roomView?.text.toString()
                val price = priceView?.text.toString()

                for(i in numberRoom.indices){
                    if( numberRoom[i] == room){
                        //priceModel.onSavePrice(i,price)
                        break
                    }
                }
                btn.cancel()
                activity?.recreate()
            }
            alertDialog.setNegativeButton("Salir"){ btn,_ ->
                btn.cancel()
            }
            alertDialog.create()
        } ?:throw IllegalStateException("Activity is null!")
    }
}