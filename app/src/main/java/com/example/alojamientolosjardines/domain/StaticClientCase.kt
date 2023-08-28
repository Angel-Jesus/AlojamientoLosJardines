package com.example.alojamientolosjardines.domain

import com.example.alojamientolosjardines.data.StaticRepository
import com.example.alojamientolosjardines.data.model.ClienteModel

class StaticClientCase {
    private val reposity = StaticRepository()
    operator fun invoke(staticList: ArrayList<ClienteModel>):Boolean = reposity.staticData(staticList)
}