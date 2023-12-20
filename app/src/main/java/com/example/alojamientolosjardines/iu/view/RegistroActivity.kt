package com.example.alojamientolosjardines.iu.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.data.network.NetworkConnection
import com.example.alojamientolosjardines.databinding.ActivityRegistroBinding
import com.example.alojamientolosjardines.iu.view.alertView.DialogProgressShow
import com.example.alojamientolosjardines.iu.viewmodel.ClienteViewModel
import com.example.alojamientolosjardines.iu.viewmodel.PickerViewModel
import com.example.alojamientolosjardines.iu.viewmodel.RoomViewModel

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private val clientes : ClienteViewModel by viewModels()
    private val picker: PickerViewModel by viewModels()
    private val loadingView = DialogProgressShow(this)
    private lateinit var networkConnection: NetworkConnection
    var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Toolbar
        toolbar_back()

        //LiveData
        picker.date.observe(this) {
            binding.editFecha.setText(it)
        }

        picker.hour.observe(this) {
            binding.editHora.setText(it)
        }

        clientes.isLoadingProgress.observe(this) {
            if (it.not()) {
                loadingView.star()
                state = true
            } else if (state) {
                loadingView.close()
                state = it.not()
            }
        }

        clientes.errorConnectionSend.observe(this) {
            if (it.not()) {
                val txt = "No se pudo enviar los datos al registro. Revice su conexion a internet"
                messageAlert(txt)
            } else {
                clientes.onCreate()
            }
        }

        //Acciones de botones
        binding.editFecha.setOnClickListener {
            picker.onDate(this)
        }

        binding.editHora.setOnClickListener {
            picker.onHour(this)
        }

        binding.btnGuardar.setOnClickListener {
            getDataToSend()
        }

        binding.btnConsulta.setOnClickListener { startActivity(Intent(this, ConsultaActivity::class.java)) }
    }

    private fun getDataToSend(){
        val Habitacion = binding.editHabitaciones.text.toString()
        val date = binding.editFecha.text.toString()
        val Hora = binding.editHora.text.toString()
        val AyN = binding.editApellidos.text.toString()
        val Dni = binding.editDni.text.toString()
        val Precio = binding.editPrecio.text.toString()
        val Procedencia = binding.editProcedencia.text.toString()
        val Observaciones = binding.editObservaciones.text.toString()

        //------Condicion de que los campos obligatorios no esten vacios-----
        val conditional = Habitacion.isEmpty().not() && date.isEmpty().not() && Hora.isEmpty().not() && AyN.isEmpty().not() && Dni.isEmpty().not() && Precio.isEmpty().not() && Procedencia.isEmpty().not()

        if(conditional)
        {
            val data = arrayOf(Habitacion,date,Hora,AyN,Dni,Precio,Procedencia,Observaciones,"POST","'")
            clientes.onSendRequest(data)
        }
        else
        {
            val txt = "Asegurese de no dejar un campo vacio. Rellene correctamente los datos requeridos, solo el campo de observaciones no es obligatorio rellenar."
            messageAlert(txt)
        }
    }

    private fun messageAlert(message:String){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Mensaje: Tenemos un problema")
        alert.setMessage(message)
        alert.setCancelable(true)
        alert.setPositiveButton("OK"){btn, _ ->
            btn.cancel()
        }
        alert.show()
    }

    override fun onStart() {
        super.onStart()
        networkConnection = NetworkConnection(application)
        networkConnection.observe(this) {
            when {
                it -> {
                    clientes.onCreate()
                }
                else -> {
                    val txt = "No se pudo acceder a la base de datos. Revice su conexion a internet"
                    messageAlert(txt)
                    binding.btnGuardar.isEnabled = false
                    binding.btnConsulta.isEnabled = false
                }
            }
        }
    }

    //Funcion para crear el toolbar en el screen
    private fun toolbar_back(){
        val back_menu = supportActionBar
        back_menu!!.title = "Regresar"
        back_menu.setDisplayHomeAsUpEnabled(true)
        back_menu.setHomeAsUpIndicator(R.drawable.icon_back)
    }

    //Funcion para retornar al anterior screen de la aplicacion
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this,MainActivity::class.java))
        return true
    }
}