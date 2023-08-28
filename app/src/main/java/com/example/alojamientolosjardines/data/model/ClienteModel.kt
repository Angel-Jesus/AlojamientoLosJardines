package com.example.alojamientolosjardines.data.model

import com.google.gson.annotations.SerializedName

data class ClienteModel (
    @SerializedName("N° habitaciones") val habitacion:String,
    @SerializedName("Fecha") val fecha:String,
    @SerializedName("Hora") val hora:String,
    @SerializedName("Apellidos y Nombres") val apellido:String,
    @SerializedName("DNI") val dni:String,
    @SerializedName("Precio") val precio:String,
    @SerializedName("Procedencia") val procedencia:String,
    @SerializedName("Observaciones") val observaciones:String,
    @SerializedName("id") val id:String
    )