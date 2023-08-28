package com.example.alojamientolosjardines.domain

import com.example.alojamientolosjardines.data.model.ClienteModel
import com.example.alojamientolosjardines.data.model.StatiticProvider
import java.util.*

class filterClientCase {

    fun getClient(pos:Int, monthSelected:Int, txt:String, lista: List<ClienteModel>): ArrayList<ClienteModel>{
        val arrayForm = arrayListOf(ClienteModel("","","","","","","","",""))
        arrayForm.clear()
        //--------------Datos fecha actual-----------------------------------
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)
        val date = if(month < 10){ "0$month/$year" } else { "$month/$year" }
        //--------------------------------------------------------------------
        val monthString = if(monthSelected < 10){ "0$monthSelected" }else{ monthSelected.toString() }
        val itResult = lista.size
        var counter = 0
        for(i in (itResult - 1) downTo 0){
            when(pos){
                0 -> {
                    if(lista[i].fecha.substring(3,10) == date){
                        arrayForm.add(counter,ClienteModel(
                            lista[i].habitacion,
                            lista[i].fecha,
                            lista[i].hora,
                            lista[i].apellido,
                            lista[i].dni,
                            lista[i].precio,
                            lista[i].procedencia,
                            lista[i].observaciones,
                            lista[i].id
                        ))
                        counter++
                    }
                }

                1 -> {
                    if(monthSelected == 0){
                        if(lista[i].procedencia.lowercase() == txt){
                            arrayForm.add(counter,ClienteModel(
                                lista[i].habitacion,
                                lista[i].fecha,
                                lista[i].hora,
                                lista[i].apellido,
                                lista[i].dni,
                                lista[i].precio,
                                lista[i].procedencia,
                                lista[i].observaciones,
                                lista[i].id
                            ))
                            counter++
                        }
                    }
                    else if(monthSelected <= month){
                        if(lista[i].procedencia.lowercase() == txt && lista[i].fecha.substring(3,10) == "$monthString/$year"){
                            arrayForm.add(counter,ClienteModel(
                                lista[i].habitacion,
                                lista[i].fecha,
                                lista[i].hora,
                                lista[i].apellido,
                                lista[i].dni,
                                lista[i].precio,
                                lista[i].procedencia,
                                lista[i].observaciones,
                                lista[i].id
                            ))
                            counter++
                        }
                    }
                    else{
                        if(lista[i].procedencia.lowercase() == txt && lista[i].fecha.substring(3,10) == "$monthString/${year-1}"){
                            arrayForm.add(counter,ClienteModel(
                                lista[i].habitacion,
                                lista[i].fecha,
                                lista[i].hora,
                                lista[i].apellido,
                                lista[i].dni,
                                lista[i].precio,
                                lista[i].procedencia,
                                lista[i].observaciones,
                                lista[i].id
                            ))
                            counter++
                        }
                    }
                }

                2 -> {
                    if(monthSelected <= month){
                        if(lista[i].fecha.substring(3,10) == "$monthString/$year"){
                            arrayForm.add(counter,ClienteModel(
                                lista[i].habitacion,
                                lista[i].fecha,
                                lista[i].hora,
                                lista[i].apellido,
                                lista[i].dni,
                                lista[i].precio,
                                lista[i].procedencia,
                                lista[i].observaciones,
                                lista[i].id
                            ))
                            counter++
                        }
                    }
                    else{
                        if(lista[i].fecha.substring(3,10) == "$monthString/${year-1}"){
                            arrayForm.add(counter,ClienteModel(
                                lista[i].habitacion,
                                lista[i].fecha,
                                lista[i].hora,
                                lista[i].apellido,
                                lista[i].dni,
                                lista[i].precio,
                                lista[i].procedencia,
                                lista[i].observaciones,
                                lista[i].id
                            ))
                            counter++
                        }
                    }
                }

                3 -> {
                    if(monthSelected == 0){
                        if(lista[i].dni == txt){
                            arrayForm.add(counter,ClienteModel(
                                lista[i].habitacion,
                                lista[i].fecha,
                                lista[i].hora,
                                lista[i].apellido,
                                lista[i].dni,
                                lista[i].precio,
                                lista[i].procedencia,
                                lista[i].observaciones,
                                lista[i].id
                            ))
                            counter++
                        }
                    }
                    else if(monthSelected <= month){
                        if(lista[i].dni == txt && lista[i].fecha.substring(3,10) == "$monthString/$year"){
                            arrayForm.add(counter,ClienteModel(
                                lista[i].habitacion,
                                lista[i].fecha,
                                lista[i].hora,
                                lista[i].apellido,
                                lista[i].dni,
                                lista[i].precio,
                                lista[i].procedencia,
                                lista[i].observaciones,
                                lista[i].id
                            ))
                            counter++
                        }
                    }
                    else{
                        if(lista[i].dni == txt && lista[i].fecha.substring(3,10) == "$monthString/${year-1}"){
                            arrayForm.add(counter,ClienteModel(
                                lista[i].habitacion,
                                lista[i].fecha,
                                lista[i].hora,
                                lista[i].apellido,
                                lista[i].dni,
                                lista[i].precio,
                                lista[i].procedencia,
                                lista[i].observaciones,
                                lista[i].id
                            ))
                            counter++
                        }
                    }
                }
            }
        }
        return arrayForm
    }

    fun getIncomeTotal(clientList: ArrayList<ClienteModel>):Int{
        var income = 0
        for(i in clientList.indices){
            income += clientList[i].precio.toInt()
        }
        return income
    }
}