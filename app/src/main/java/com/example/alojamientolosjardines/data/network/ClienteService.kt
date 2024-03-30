package com.example.alojamientolosjardines.data.network

import android.util.Log
import com.example.alojamientolosjardines.core.RetrofitHelper
import com.example.alojamientolosjardines.data.model.ClienteModel
import com.example.alojamientolosjardines.data.model.RoomStateModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ClienteService {

    private val retrofitGetClientList = RetrofitHelper.getRetrofitList()
    private val retrofitRequestClient = RetrofitHelper.requestRetrofitData()

    suspend fun getCliente(): List<ClienteModel>{
        return withContext(Dispatchers.IO){
            try {
                val response = retrofitGetClientList.create(ClienteApiClient::class.java).getAllCliente()
                response.body() ?: emptyList()
            }catch(e: Exception){
                emptyList()
            }
        }
    }

    suspend fun requestCliente(data:Array<String>):Boolean{
        return withContext(Dispatchers.IO){
            try {
                data.forEach { Log.d("clientes", it)  }
                val response = retrofitRequestClient.create(ClienteApiClient::class.java).requestCliente(
                    data[0],
                    "'${data[1]}",
                    "'${data[2]}",
                    data[3],
                    data[4],
                    data[5],
                    data[6],
                    data[7],
                    data[8],
                    data[9],
                )
                response.isSuccessful
            }catch(e: Exception){
                false
            }
        }
    }

    suspend fun requestSendStateRoom(data:Array<String>):Boolean{
        return withContext(Dispatchers.IO){
            try {
                val response = retrofitRequestClient.create(ClienteApiClient::class.java).sendStateRoom(
                    data[0],
                    data[1],
                    data[2]
                )
                response.isSuccessful
            }catch(e: Exception){
                false
            }
        }
    }

    suspend fun requestGetListStateRoom():List<RoomStateModel>{
        return withContext(Dispatchers.IO){
            try
            {
                val response1 = retrofitRequestClient.create(ClienteApiClient::class.java).getStateRoom("GET")
                response1.body() ?: emptyList()
            }
            catch(e: Exception)
            {
                emptyList()
            }
        }
    }


}