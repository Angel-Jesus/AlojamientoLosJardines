package com.example.alojamientolosjardines.iu.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.data.model.RoomModel
import com.example.alojamientolosjardines.data.recycleView.BedAdapter
import com.example.alojamientolosjardines.databinding.ActivityRoomBinding
import com.example.alojamientolosjardines.iu.view.alertView.DialogProgressShow
import com.example.alojamientolosjardines.iu.view.dialogFragment.DialogFragmentOption
import com.example.alojamientolosjardines.iu.view.dialogFragment.DialogFragmentSetting
import com.example.alojamientolosjardines.iu.viewmodel.RoomViewModel

class RoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding
    private val room : RoomViewModel by viewModels()
    private var isGetFinish = false
    private var listStateRoom = ArrayList<RoomModel>()
    private val loadingView = DialogProgressShow(this)
    private var positionItem = 0
    private var state = false
    private lateinit var adapter: BedAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarBack()

        room.roomList.observe(this){ list_room ->
            listStateRoom = list_room
            if(listStateRoom.size != 0 && isGetFinish)
            {
                adapter = BedAdapter(this, listStateRoom){ BedState ->
                    onItemSelected(
                        BedState
                    )
                }
                binding.recycleBed.setHasFixedSize(true)
                val linearLayoutManager = LinearLayoutManager(this)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                binding.recycleBed.layoutManager = linearLayoutManager
                binding.recycleBed.adapter = adapter
            }

        }

        room.isLoadingGet.observe(this){
            if(!it)
            {
                binding.isLoading.isGone = false
                binding.recycleBed.isGone = true
                isGetFinish = false
            }
            else
            {
                binding.isLoading.isGone = true
                binding.recycleBed.isGone = false
                isGetFinish = true
            }
        }

        room.isLoadingSend.observe(this) {
            if (it.not()) {
                loadingView.star()
                state = true
            } else if (state) {
                loadingView.close()
                state = it.not()
            }
        }

        room.isOK.observe(this){
            if(it.not())
            {
                messageAlertError()
            }
            else
            {
                adapter.notifyItemChanged(positionItem)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        room.onGetListStateRoom()
    }

    private fun onItemSelected(position: Int){
        positionItem = position
        messageAlert(position.toString())
    }


    private fun messageAlert(position: String){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Mensaje: Actualización ")
        alert.setMessage("Esta por actualizar la disponibilidad de la habitación. ¿Desea continuar?")
        alert.setCancelable(true)
        alert.setPositiveButton("Actualizar"){btn, _ ->
            room.onSendStateRoom(arrayOf("SEND", position, ""), listStateRoom, position.toInt())
            btn.cancel()
        }
        alert.setNegativeButton("NO"){btn, _ ->
            btn.cancel()
        }
        alert.show()
    }

    private fun messageAlertError(){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Mensaje: Error ")
        alert.setMessage("Ocurrio un problema, se requiere volver a enviar la solicitud de actualizacion")
        alert.setCancelable(true)
        alert.setPositiveButton("OK"){btn, _ ->
            btn.cancel()
        }
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.setting -> {
                DialogFragmentSetting().show(supportFragmentManager,"dialogFragmentSetting")
                return true
            }else -> super.onOptionsItemSelected(item)
        }
    }

    //Funcion para crear el toolbar en el screen
    private fun toolbarBack(){
        val backMenu = supportActionBar
        backMenu!!.title = "Regresar"
        backMenu.setDisplayHomeAsUpEnabled(true)
        backMenu.setHomeAsUpIndicator(R.drawable.icon_back)
    }

    //Funcion para retornar al anterior screen de la aplicacion
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this,MainActivity::class.java))
        return true
    }
}