package com.example.alojamientolosjardines.domain

import com.example.alojamientolosjardines.data.ClienteRepository

class SendStateRoom(private val data:Array<String>) {
    private val reposity = ClienteRepository()
    suspend operator fun invoke() = reposity.requestSendStateRoom(data)
}