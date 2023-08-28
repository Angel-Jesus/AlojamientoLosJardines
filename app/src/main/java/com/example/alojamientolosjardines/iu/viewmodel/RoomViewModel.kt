package com.example.alojamientolosjardines.iu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alojamientolosjardines.di.DatabaseModule
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {
    val roomStateList = MutableLiveData<ArrayList<Boolean>>()
    val roomPriceList = MutableLiveData<ArrayList<String>>()
    private val roomProvider = DatabaseModule.roomProvider

    fun onGetState(){
        val stateRoomList = roomProvider.getStateRoom()
        roomStateList.postValue(stateRoomList)
    }

    fun onGetPrice(){
        val priceRoomList = roomProvider.getPriceRoom()
        roomPriceList.postValue(priceRoomList)
    }

    fun onSaveState(i:Int, state:Boolean){
        roomProvider.saveStateRoom(i,state)
        onGetState()
    }

    fun onSavePrice(i:Int, price:String){
        roomProvider.savePriceRoom(i,"S/.$price")
        onGetPrice()
    }

}