package com.example.alojamientolosjardines.data

import com.example.alojamientolosjardines.data.model.RoomModel
import com.example.alojamientolosjardines.data.model.RoomStateModel
import com.example.alojamientolosjardines.di.DatabaseModule

class RoomRepository {
    private val roomProvider = DatabaseModule.roomProvider

    fun getListRoom(ListStateRoom: List<RoomStateModel>):ArrayList<RoomModel>{
        val roomNumberH = roomProvider.roomNumber
        val roomPriceH = roomProvider.getPriceRoom()
        val roomListModel = ArrayList<RoomModel>()

        for(i in roomNumberH.indices)
        {
            roomListModel.add(RoomModel(
                roomNumberH[i],
                ListStateRoom[i].roomState,
                roomPriceH[i]
            ))
        }
        return roomListModel
    }
}