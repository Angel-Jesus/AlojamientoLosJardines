package com.example.alojamientolosjardines.iu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alojamientolosjardines.data.model.ClienteModel
import com.example.alojamientolosjardines.data.model.StatiticProvider
import com.example.alojamientolosjardines.domain.GetClienteCase
import com.example.alojamientolosjardines.domain.RequestClienteCase
import com.example.alojamientolosjardines.domain.StaticClientCase
import com.example.alojamientolosjardines.domain.filterClientCase
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ClienteViewModel: ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val isLoadingProgress = MutableLiveData<Boolean>()
    val isLoadingStatic = MutableLiveData<Boolean>()
    val getFiltroCase = MutableLiveData<ArrayList<ClienteModel>>()
    val errorConnectionSend = MutableLiveData<Boolean>()
    val lastId = MutableLiveData<Int>()
    val isClient = MutableLiveData<Array<String>>()
    val incomeTotal = MutableLiveData<Int>()
    var getClienteCase = GetClienteCase()
    private val filter = filterClientCase()
    private val static = StaticClientCase()

    fun onCreate(pos:Int = 0, mes:Int = 0, txt:String = ""){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getClienteCase() //lista de todos los cliente de la base de datos
            if(!result.isNullOrEmpty()){
                val id = result[result.size - 1].id.toInt()
                val data = filter.getClient(pos, mes, txt.lowercase(), result)
                val income = filter.getIncomeTotal(data)
                getFiltroCase.postValue(data)
                incomeTotal.postValue(income)
                lastId.postValue(id)
            }
            isLoading.postValue(false)
        }
    }

    fun onSendRequest(data:Array<String>){
        viewModelScope.launch {
            //colocar un progress bar
            isLoadingProgress.postValue(false)
            val request = RequestClienteCase(data)
            val response = request()
            errorConnectionSend.postValue(response)
            if(response){ lastId.postValue(0) }
            isLoadingProgress.postValue(true)
        }
    }

    fun onStartStaticRequest(staticList: ArrayList<ClienteModel>){
        viewModelScope.launch {
            isLoadingStatic.postValue(false)
            val response = static(staticList)
            isLoadingStatic.postValue(response)
        }
    }

}