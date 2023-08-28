package com.example.alojamientolosjardines.iu.view.dialogFragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.alojamientolosjardines.R
import com.google.android.material.textfield.TextInputEditText

class DialogFragmentPassword(private val income:Int) : DialogFragment() {
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val alertDialog = AlertDialog.Builder(it)
            alertDialog.setView(requireActivity().layoutInflater.inflate(R.layout.view_password,null))
            alertDialog.setPositiveButton("Ingresar"){btn,_ ->
                val txt = dialog?.findViewById<TextInputEditText>(R.id.edit_password)
                val password = txt?.text.toString()
                if(password == "siempreadelante"){
                    Toast.makeText(requireContext(),"S/. $income total", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(),"Contraseña incorrecta, intentar de nuevo", Toast.LENGTH_LONG).show()
                }
                btn.cancel()
            }
            alertDialog.setNegativeButton("Salir"){btn,_ ->
                btn.cancel()
            }
            alertDialog.create()
        }?:throw IllegalStateException("Activity is null!")
    }
}