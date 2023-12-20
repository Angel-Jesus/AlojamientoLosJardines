package com.example.alojamientolosjardines.iu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alojamientolosjardines.data.RoomRepository
import com.example.alojamientolosjardines.data.model.RoomModel
import com.example.alojamientolosjardines.domain.GetStateRoom
import com.example.alojamientolosjardines.domain.SendStateRoom
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {
    private val roomRepository = RoomRepository()
    val roomList = MutableLiveData<ArrayList<RoomModel>>()
    val isLoadingGet = MutableLiveData<Boolean>()
    val isLoadingSend = MutableLiveData<Boolean>()
    val getStateRoom = GetStateRoom()
    val isOK = MutableLiveData<Boolean>()

    fun onGetListStateRoom(){
        viewModelScope.launch {
            isLoadingGet.postValue(false)
            val stateRoomList1 = getStateRoom()
            if(stateRoomList1.isNotEmpty())
            {
                isLoadingGet.postValue(true)
                val listStateRoom = roomRepository.getListRoom(stateRoomList1)
                roomList.postValue(listStateRoom)
            }
        }
    }

    fun onSendStateRoom(stateRoom: Array<String>, stateRoomInit: ArrayList<RoomModel>,position: Int){
        viewModelScope.launch {
            isLoadingSend.postValue(false)
            val response = SendStateRoom(stateRoom)()
            isLoadingSend.postValue(true)
            if (response)
            {
                val state = if(stateRoomInit[position].roomState == "DISPONIBLE"){ "OCUPADO" }else{ "DISPONIBLE" }
                stateRoomInit[position].roomState = state
                roomList.postValue(stateRoomInit)
                isOK.postValue(true)
            }
            else
            {
                isOK.postValue(false) // ocurrio un problema
            }
        }
    }
}