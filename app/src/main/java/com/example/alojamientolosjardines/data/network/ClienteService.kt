package com.example.alojamientolosjardines.data.network

import com.example.alojamientolosjardines.core.RetrofitHelper
import com.example.alojamientolosjardines.data.model.ClienteModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ClienteService {

    private val retrofit = RetrofitHelper.getRetrofitList()
    private val retrofit2 = RetrofitHelper.requestRetrofitData()

    suspend fun getCliente(): List<ClienteModel>{
        return withContext(Dispatchers.IO){
            try {
                val response = retrofit.create(ClienteApiClient::class.java).getAllCliente()
                response.body() ?: emptyList()
            }catch(e: Exception){
                emptyList()
            }
        }
    }

    suspend fun requestCliente(data:Array<String>):Boolean{
        return withContext(Dispatchers.IO){
            try {
                val response = retrofit2.create(ClienteApiClient::class.java).requestCliente(
                    data[0],
                    data[1],
                    data[2],
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
}