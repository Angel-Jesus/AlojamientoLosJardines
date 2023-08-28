package com.example.alojamientolosjardines.iu.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.databinding.ActivityMainBinding
import com.example.alojamientolosjardines.iu.viewmodel.ClienteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AlojamientoLosJardines)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnConsulta.setOnClickListener {startActivity(Intent(this,ConsultaActivity::class.java))}

        binding.btnRegistro.setOnClickListener {startActivity(Intent(this,RegistroActivity::class.java))}

        binding.btnHabitaciones.setOnClickListener {startActivity(Intent(this,RoomActivity::class.java))}

    }
}