package com.example.alojamientolosjardines.data.model

import android.annotation.SuppressLint
import android.content.Context

class RoomProvider(context: Context){

    private val roomPriceKey = "roomPriceKey"
    //private val roomNumberKey = "roomNumberKey"
    //private val roomStateKey = "roomStateKey"

    val roomNumber = listOf("101","102","103","104","105","106","107","108","201","202","203","204","205","206","207")


    private val settingPrice = context.getSharedPreferences(roomPriceKey,0)

    /*
    private val settingRoom = context.getSharedPreferences(roomNumberKey,0)
    private val settingState = context.getSharedPreferences(roomStateKey,0)

    @SuppressLint("CommitPrefEdits")
    fun saveStateRoom(i:Int, state:Boolean){
        settingState.edit().putBoolean(roomNumber[i],state).apply()
    }

    fun getStateRoom():ArrayList<Boolean>{
        val stateRoom = arrayListOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)
        for(i in roomNumber.indices){
            stateRoom[i] = settingState.getBoolean(roomNumber[i],false)
        }
        return stateRoom
    }
    */

    fun savePriceRoom(i:Int, price:String){
        settingPrice.edit().putString(roomNumber[i],price).apply()
    }

    fun getPriceRoom():ArrayList<String>{
        val priceRoom = arrayListOf("S/.35","S/.35","S/.35","S/.35","S/.65","S/.55","S/.45","S/.45","S/.80","S/.55","S/.45","S/.45","S/.55","S/.55","S/.55")
        for(i in roomNumber.indices){
            if(settingPrice.getString(roomNumber[i],"") != ""){
                priceRoom[i] = settingPrice.getString(roomNumber[i],"").toString()
            }
        }
        return priceRoom
    }

}