package com.example.alojamientolosjardines.data.network

import com.example.alojamientolosjardines.data.model.ClienteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ClienteApiClient {

    @GET("AKfycbwxMRY1FCYzN33u8gVruRFVrPV2_hbcEMjmtsSD2isPjsyfZpuvxrMH4i1h3aDsx2s_/exec")
    suspend fun getAllCliente(): Response<List<ClienteModel>>

    @GET("AKfycbwxMRY1FCYzN33u8gVruRFVrPV2_hbcEMjmtsSD2isPjsyfZpuvxrMH4i1h3aDsx2s_/exec")
    suspend fun requestCliente(
        @Query("habitacion") habitacion:String = "",
        @Query("fecha") fecha:String = "",
        @Query("hora") hora:String = "",
        @Query("apellidos") apellido:String = "",
        @Query("dni") dni:String = "",
        @Query("precio") precio:String = "",
        @Query("procedencia") procedencia:String = "",
        @Query("observacion") observacion:String = "",
        @Query("accion") accion:String = "",
        @Query("id") id:String = "",
    ): Response<Unit>
}