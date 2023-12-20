package com.example.alojamientolosjardines.domain

import com.example.alojamientolosjardines.data.ClienteRepository
import com.example.alojamientolosjardines.data.model.ClienteModel

class GetClienteCase {
    private val reposity = ClienteRepository()
    suspend operator fun invoke(): List<ClienteModel> = reposity.getAllClientes()
}