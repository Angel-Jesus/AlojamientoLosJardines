package com.example.alojamientolosjardines.data.network

import com.example.alojamientolosjardines.data.model.ClienteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ClienteApiClient {

    @GET("AKfycbzeq6AE3bVl6iNkA32j3SCKHcxiM42d1yjMuZy03NQKgO1lFLs8-TNKcDIZmdWD89GcHg/exec")
    suspend fun getAllCliente(): Response<List<ClienteModel>>

    @GET("1FAIpQLSf00TdVUZYuJtj8gMJf1NfNmFDJnnY3SP4unjaijeHKFOygPg/formResponse?usp=pp_url")
    suspend fun requestCliente(
        @Query("entry.1355511849") habitacion:String = "",
        @Query("entry.1239323002") fecha:String = "",
        @Query("entry.310648117") hora:String = "",
        @Query("entry.1438208562") apellido:String = "",
        @Query("entry.397742594") dni:String = "",
        @Query("entry.1625760868") precio:String = "",
        @Query("entry.146480289") procedencia:String = "",
        @Query("entry.703144027") observacion:String = "",
        @Query("entry.240160237") accion:String = "",
        @Query("entry.368901014") id:String = "",
    ): Response<Unit>
}