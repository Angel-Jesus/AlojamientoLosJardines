package com.example.alojamientolosjardines.data.model

import com.google.gson.annotations.SerializedName

data class RoomStateModel(
    @SerializedName("Habitacion") val roomNumber:String,
    @SerializedName("Estado") val roomState:String
)