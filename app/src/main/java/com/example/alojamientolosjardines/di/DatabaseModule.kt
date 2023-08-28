package com.example.alojamientolosjardines.di

import android.app.Application
import com.example.alojamientolosjardines.data.model.RoomProvider

class DatabaseModule: Application() {
    companion object {
        lateinit var roomProvider: RoomProvider
    }

    override fun onCreate(){
        super.onCreate()
        roomProvider = RoomProvider(applicationContext)
    }
}