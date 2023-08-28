package com.example.alojamientolosjardines.iu.view.alertView

import android.app.Activity
import android.app.AlertDialog
import com.example.alojamientolosjardines.R

class DialogProgressShow(val activity:Activity) {
    private lateinit var isDialog: AlertDialog

    fun star(){
        val dialogView = activity.layoutInflater.inflate(R.layout.view_progress,null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }

    fun close(){
        isDialog.dismiss()
    }
}