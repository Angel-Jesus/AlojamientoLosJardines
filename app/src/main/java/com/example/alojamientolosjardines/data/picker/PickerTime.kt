package com.example.alojamientolosjardines.data.picker

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class PickerTime(val listener: (String) -> Unit):DialogFragment(), TimePickerDialog.OnTimeSetListener{
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minuteOfday: Int) {
        val hour = if(hourOfDay < 10) { "0$hourOfDay" }else { hourOfDay.toString() }
        val minute = if(minuteOfday < 10) { "0$minuteOfday" }else { minuteOfday.toString() }
        listener("$hour:$minute")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = Calendar.getInstance()
        val hour:Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute:Int = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(activity as Context, this, hour, minute, false)
    }
}