package com.example.alojamientolosjardines.data.model

import com.google.gson.annotations.SerializedName

data class ClienteModel (
    @SerializedName("habitacion") val habitacion:String,
    @SerializedName("fecha") val fecha:String,
    @SerializedName("hora") val hora:String,
    @SerializedName("apellidos") val apellido:String,
    @SerializedName("dni") val dni:String,
    @SerializedName("precio") val precio:String,
    @SerializedName("procedencia") val procedencia:String,
    @SerializedName("observacion") val observaciones:String,
    @SerializedName("id") val id:String
    )