package com.example.alojamientolosjardines.iu.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.databinding.ActivityRoomBinding
import com.example.alojamientolosjardines.iu.view.dialogFragment.DialogFragmentSetting
import com.example.alojamientolosjardines.iu.viewmodel.RoomViewModel

class RoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding
    private val room : RoomViewModel by viewModels()
    private var stateList = ArrayList<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarBack()

        val imagenButtonBinding = listOf(
            binding.h101,
            binding.h102,
            binding.h103,
            binding.h104,
            binding.h105,
            binding.h106,
            binding.h107,
            binding.h108,
            binding.h201,
            binding.h202,
            binding.h203,
            binding.h204,
            binding.h205,
            binding.h206,
            binding.h207)

        val stateRoomBinding = listOf(
            binding.L101,
            binding.L102,
            binding.L103,
            binding.L104,
            binding.L105,
            binding.L106,
            binding.L107,
            binding.L108,
            binding.L201,
            binding.L202,
            binding.L203,
            binding.L204,
            binding.L205,
            binding.L206,
            binding.L207,
        )

        val priceRoomBinding = listOf(
            binding.h101S,
            binding.h102S,
            binding.h103S,
            binding.h104S,
            binding.h105S,
            binding.h106S,
            binding.h107S,
            binding.h108S,
            binding.h201S,
            binding.h202S,
            binding.h203S,
            binding.h204S,
            binding.h205S,
            binding.h206S,
            binding.h207S,
        )


        room.roomStateList.observe(this, {
            stateList = it
            for(i in stateList.indices){
                if(stateList[i]){
                    imagenButtonBinding[i].setImageResource(R.drawable.habitacion_off)
                    stateRoomBinding[i].setText(R.string.Ho)
                }else{
                    imagenButtonBinding[i].setImageResource(R.drawable.habitacion_on)
                    stateRoomBinding[i].setText(R.string.Hd)
                }
            }
        })

        room.roomPriceList.observe(this, {
            for(i in it.indices){
                priceRoomBinding[i].text = it[i]
            }
        })

        binding.h101.setOnClickListener { room.onSaveState(0,stateList[0].not()) }
        binding.h102.setOnClickListener { room.onSaveState(1,stateList[1].not()) }
        binding.h103.setOnClickListener { room.onSaveState(2,stateList[2].not()) }
        binding.h104.setOnClickListener { room.onSaveState(3,stateList[3].not()) }
        binding.h105.setOnClickListener { room.onSaveState(4,stateList[4].not()) }
        binding.h106.setOnClickListener { room.onSaveState(5,stateList[5].not()) }
        binding.h107.setOnClickListener { room.onSaveState(6,stateList[6].not()) }
        binding.h108.setOnClickListener { room.onSaveState(7,stateList[7].not()) }
        binding.h201.setOnClickListener { room.onSaveState(8,stateList[8].not()) }
        binding.h202.setOnClickListener { room.onSaveState(9,stateList[9].not()) }
        binding.h203.setOnClickListener { room.onSaveState(10,stateList[10].not()) }
        binding.h204.setOnClickListener { room.onSaveState(11,stateList[11].not()) }
        binding.h205.setOnClickListener { room.onSaveState(12,stateList[12].not()) }
        binding.h206.setOnClickListener { room.onSaveState(13,stateList[13].not()) }
        binding.h207.setOnClickListener { room.onSaveState(14,stateList[14].not()) }

    }

    override fun onStart() {
        super.onStart()
        room.onGetState()
        room.onGetPrice()
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