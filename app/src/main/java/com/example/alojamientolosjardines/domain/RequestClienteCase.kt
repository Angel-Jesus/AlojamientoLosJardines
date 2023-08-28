package com.example.alojamientolosjardines.domain

import com.example.alojamientolosjardines.data.ClienteRepository

class RequestClienteCase(private val data:Array<String>) {
    private val reposity = ClienteRepository()
    suspend operator fun invoke() = reposity.requestDataClientes(data)
}