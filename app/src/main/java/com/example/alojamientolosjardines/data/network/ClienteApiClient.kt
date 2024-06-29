package com.example.alojamientolosjardines.data.network

import com.example.alojamientolosjardines.data.model.ClienteModel
import com.example.alojamientolosjardines.data.model.RoomStateModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ClienteApiClient {

    // Link to connection googleSheet: ID/exec
    
    @GET("Link")
    suspend fun getAllCliente(): Response<List<ClienteModel>>

    @GET("Link")
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
        @Query("id") id:String = ""
    ): Response<Unit>

    @GET("Link")
    suspend fun sendStateRoom(
        @Query("accion") accion:String = "",
        @Query("habitacion") habitacion:String = "",
        @Query("observacion") observacion:String = ""
    ): Response<Unit>

    @GET("Link")
    suspend fun getStateRoom(
        @Query("accion") accion:String = ""
    ): Response<List<RoomStateModel>>
}
