package com.example.alojamientolosjardines.iu.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.data.network.NetworkConnection
import com.example.alojamientolosjardines.data.recycleView.ClientesAdapter
import com.example.alojamientolosjardines.databinding.ActivityConsultaBinding
import com.example.alojamientolosjardines.iu.view.alertView.DialogProgressShow
import com.example.alojamientolosjardines.iu.view.dialogFragment.DialogFragmentOption
import com.example.alojamientolosjardines.iu.view.dialogFragment.DialogFragmentPassword
import com.example.alojamientolosjardines.iu.viewmodel.ClienteViewModel

class ConsultaActivity : AppCompatActivity() {
    private lateinit var binding:ActivityConsultaBinding
    private val clientes : ClienteViewModel by viewModels()
    private lateinit var networkConnection: NetworkConnection
    private val loadingView = DialogProgressShow(this)
    var state = false
    var income = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Toolbar de regreso a la anterior ventana
        toolbar_back()

        binding.btnFiltro.isEnabled = false

        //-----Sección de view para Spinner con los datos para el filtrado------
        val lista_filtro = listOf("Aplicar Filtro", "Procedencia", "Mes", "DNI")
        val adaptadorFiltro = ArrayAdapter(this, R.layout.spinner_item_text, lista_filtro)
        binding.spinnerFiltro.adapter = adaptadorFiltro

        val lista_mes = listOf(
            "Seleccionar Mes",
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"
        )

        val adaptadorMes = ArrayAdapter(this, R.layout.spinner_item_text, lista_mes)
        binding.spinnerMes.adapter = adaptadorMes

        binding.spinnerFiltro.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        binding.spinnerMes.isGone = true
                        binding.completar.isGone = true
                        binding.btnFiltro.isGone = true
                        binding.btnLimpiar.isGone = true
                    }

                    1 -> {
                        binding.spinnerMes.isVisible = true
                        binding.completar.isVisible = true
                        binding.btnFiltro.isVisible = true
                        binding.btnLimpiar.isVisible = true
                    }

                    2 -> {
                        binding.completar.isGone = true
                        binding.spinnerMes.isVisible = true
                        binding.btnFiltro.isVisible = true
                        binding.btnLimpiar.isVisible = true
                    }

                    3 -> {
                        binding.spinnerMes.isVisible = true
                        binding.completar.isVisible = true
                        binding.btnFiltro.isVisible = true
                        binding.btnLimpiar.isVisible = true
                    }
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        // ---------------------------------------------------------------------

        //----------Seccion de mutableLiveData---------
        clientes.isLoading.observe(this) {
            binding.progressBar.isGone = it.not()
            binding.recyclerClientes.isGone = it
            if (it.not() && binding.spinnerFiltro.selectedItemPosition == 0)
            {
                binding.recyclerClientes.setHasFixedSize(true)
                binding.recyclerClientes.layoutManager = LinearLayoutManager(this)
                clientes.getFiltroCase.observe(this) { ListaFiltrada ->
                    binding.recyclerClientes.adapter =
                        ClientesAdapter(ListaFiltrada) { item, indice -> onItemSelected(item, indice) }
                    binding.cantidad.text = ListaFiltrada.size.toString()
                }
            }
        }

        clientes.isLoadingProgress.observe(this) {
            if (it.not()) {
                loadingView.star()
                state = true
                Toast.makeText(this, "Dato actualizado", Toast.LENGTH_LONG).show()
            } else if (state) {
                loadingView.close()
                state = it.not()
                
            }
        }

        clientes.isClient.observe(this) {
            if (it.isEmpty().not()) {
                clientes.onSendRequest(it)
            }
        }

        clientes.incomeTotal.observe(this) {
            income = it
        }
        // -----------------------------------------

        //Seccion de accion de los botones
        binding.btnFiltro.setOnClickListener {
            val posicion = binding.spinnerFiltro.selectedItemPosition
            val mes = binding.spinnerMes.selectedItemPosition
            val text = binding.editCompletar.text.toString()
            when {
                text.isEmpty() && posicion == 1 -> {
                    val txt = "El campo de filtro no debe estar vacio"
                    messageAlert(txt)
                }
                mes == 0 && posicion == 2 -> {
                    val txt = "Seleccione un Mes"
                    messageAlert(txt)
                }
                else -> {
                    clientes.onCreate(posicion,mes,text)
                }
            }
        }

        binding.btnLimpiar.setOnClickListener{
            clientes.onCreate()
            binding.spinnerMes.isGone = true
            binding.completar.isGone = true
            binding.btnFiltro.isGone = true
            binding.btnLimpiar.isGone = true
        }

        binding.btnIngreso.setOnClickListener{
            DialogFragmentPassword(income).show(supportFragmentManager,"dialogFragmentPassword")
        }

    }

    private fun onItemSelected(clientModel: Array<String>, indice: Int){
        DialogFragmentOption(this, clientModel,indice,clientes).show(supportFragmentManager, "mydialogoption")
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

    //Funcion para crear el toolbar en el screen
    private fun toolbar_back(){
        val back_menu = supportActionBar
        back_menu!!.title = "Regresar"
        back_menu.setDisplayHomeAsUpEnabled(true)
        back_menu.setHomeAsUpIndicator(R.drawable.icon_back)
    }

    override fun onStart() {
        super.onStart()
        networkConnection = NetworkConnection(application)
        networkConnection.observe(this) {
            if (it) {
                clientes.onCreate()
                binding.btnFiltro.isEnabled = true
            } else {
                val txt = "No se pudo acceder a la base de datos. Revice su conexion a internet"
                messageAlert(txt)
                binding.progressBar.isGone = true
            }
        }
    }

    //---------Funciones para la seccion menu------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_consulta, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loading -> {
                clientes.onCreate()
                return true
            }
            R.id.dataStatic -> {
                startActivity(Intent(this,StatiticActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //--------------------------------------------------------

    //Funcion para retornar al anterior screen de la aplicacion
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this,MainActivity::class.java))
        return true
    }
}