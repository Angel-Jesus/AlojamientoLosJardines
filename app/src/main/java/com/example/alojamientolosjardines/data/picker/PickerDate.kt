package com.example.alojamientolosjardines.data.picker

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class PickerDate(val listener: (String) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onDateSet(view: DatePicker?, Year: Int, Month: Int, Day: Int) {
        val d = if(Day < 10) { "0$Day" }else { Day.toString() }
        val m = if((Month + 1) < 10) { "0${Month + 1}" }else { (Month + 1).toString() }
        listener("$d/$m/$Year")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendario = Calendar.getInstance()
        val day: Int = calendario.get(Calendar.DAY_OF_MONTH)
        val month: Int = calendario.get(Calendar.MONTH)
        val year: Int = calendario.get(Calendar.YEAR)
        return DatePickerDialog(activity as Context, this, year, month, day)
    }
}