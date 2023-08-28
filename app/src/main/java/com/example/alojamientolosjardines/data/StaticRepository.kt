package com.example.alojamientolosjardines.data

import com.example.alojamientolosjardines.data.model.ClienteModel
import com.example.alojamientolosjardines.data.model.PlaceModel
import com.example.alojamientolosjardines.data.model.StatiticProvider

class StaticRepository {
    var tableDays = StatiticProvider.initialQuantity

    fun staticData(staticList: ArrayList<ClienteModel>):Boolean {
        StatiticProvider.staticListInitializer = staticList
        val dates = ArrayList<String>()
        val places = ArrayList<String>()
        var tripleCount = 0
        //Separamos los datos de lugar y procedencia en un arrayList para realizar un mejor conteo
        // y contamos de una vez la cantidad de cuartos triples usados
        for(i in 0 until staticList.size){
            dates.add(i,staticList[i].fecha.substring(0,2))
            places.add(i,staticList[i].procedencia.replace(" ",""))
            if(staticList[i].habitacion == "201"){
                tripleCount += 1
            }
        }
        //Realizamos el conteo de cada uno de los datos repetidos que representan la cantidad total de cada dia y procedencia
        val datesQuantify = dates.groupingBy { it }.eachCount().filter { it.value > 0}
        val placesQuantify = places.groupingBy { it }.eachCount().filter { it.value > 0}

        //Obtener los keys de placesQuantify
        val keysPlaces = placesQuantify.keys.toTypedArray()

        //Total de clientes registrados en el mes
        val totalPlace = placesQuantify.values.toTypedArray().sum()
        val countPlace = placesQuantify.size

        //Rellenamos la tabla del conteo por dia de clientes
        var count = 1
        for(i in 0 until 4){
            for(j in 0 until 8){
                if(count < 32){
                    if(count < 10){
                        tableDays[i][j] = if(datesQuantify["0$count"] != null){ datesQuantify["0$count"].toString() }else{ "0" }
                    }else{
                        tableDays[i][j] = if(datesQuantify[count.toString()] != null){ datesQuantify[count.toString()].toString() }else{ "0" }
                    }
                }else{
                    tableDays[i][j] = dates.size.toString()
                }
                count++
            }
        }
        //Ordenamos los datos de procedencia
        val placeList = ArrayList<PlaceModel>()
        for(i in 0 until countPlace){
            placeList.add(i,PlaceModel(keysPlaces[i],placesQuantify[keysPlaces[i]]!!.toInt()))
        }
        placeList.add(countPlace,PlaceModel("TOTAL",totalPlace))

        //Guardamos los datos en StaticProvider
        StatiticProvider.initialQuantity = tableDays
        StatiticProvider.placeList = placeList
        StatiticProvider.tripleQuantify = tripleCount
        StatiticProvider.doubleQuantify = dates.size - tripleCount

        return true
    }
}