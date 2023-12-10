package com.example.alojamientolosjardines.iu.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alojamientolosjardines.data.picker.PickerDate
import com.example.alojamientolosjardines.data.picker.PickerTime
import com.example.alojamientolosjardines.iu.view.dialogFragment.DialogFragmentOption

class PickerViewModel: ViewModel() {
    val date = MutableLiveData<String>()
    val hour = MutableLiveData<String>()

    fun onDate(activity: AppCompatActivity){
        val datePicker = PickerDate {date.postValue(it)}
        datePicker.show(activity.supportFragmentManager, "datePicker")
    }

    fun onHour(activity: AppCompatActivity){
        val timePicker = PickerTime{hour.postValue(it)}
        timePicker.show(activity.supportFragmentManager, "timePicker")
    }
}