package com.example.alojamientolosjardines.core

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitHelper {

    fun getRetrofitList(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://script.google.com/macros/s/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    fun requestRetrofitData(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://script.google.com/macros/s/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}