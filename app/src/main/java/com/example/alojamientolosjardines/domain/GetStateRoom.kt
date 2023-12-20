package com.example.alojamientolosjardines.domain

import com.example.alojamientolosjardines.data.ClienteRepository
import com.example.alojamientolosjardines.data.model.RoomStateModel

class GetStateRoom {
    private val reposity = ClienteRepository()
    suspend operator fun invoke(): List<RoomStateModel> = reposity.requestGetStateRoom()
}