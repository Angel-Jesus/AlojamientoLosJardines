package com.example.alojamientolosjardines.data

import com.example.alojamientolosjardines.data.model.ClienteModel
import com.example.alojamientolosjardines.data.model.RoomStateModel
import com.example.alojamientolosjardines.data.network.ClienteService
import com.example.alojamientolosjardines.di.DatabaseModule

class ClienteRepository {
    private val api = ClienteService()

    //private val roomProvider = DatabaseModule.roomProvider
    //private val roomNumber = roomProvider.roomNumber

    suspend fun getAllClientes(): List<ClienteModel> {
        return api.getCliente()
    }

    suspend fun requestDataClientes(data:Array<String>): Boolean {
        //val response = api.requestCliente(data)
        /*
        //Guardar el dato en sharePreference si se logra enviar el dato al cloudDatabase
        if(response){
            for(i in roomNumber.indices){
                if(data[0] == "'${roomNumber[i]}"){
                    roomProvider.saveStateRoom(i,true)
                    break
                }
            }
        }
         */
        return api.requestCliente(data)
    }

    suspend fun requestSendStateRoom(data:Array<String>): Boolean{
        return api.requestSendStateRoom(data)
    }

    suspend fun requestGetStateRoom(): List<RoomStateModel>{
        return api.requestGetListStateRoom()
    }

}