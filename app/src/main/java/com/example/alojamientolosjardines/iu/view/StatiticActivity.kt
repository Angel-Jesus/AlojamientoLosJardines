package com.example.alojamientolosjardines.iu.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.data.model.StatiticProvider
import com.example.alojamientolosjardines.data.network.NetworkConnection
import com.example.alojamientolosjardines.data.recycleView.PlaceAdapter
import com.example.alojamientolosjardines.databinding.ActivityStatiticBinding
import com.example.alojamientolosjardines.iu.viewmodel.ClienteViewModel
import java.util.*

class StatiticActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatiticBinding
    private val clientes : ClienteViewModel by viewModels()
    private lateinit var networkConnection: NetworkConnection
    private var table: TableLayout? = null
    private val dayTxt = StatiticProvider.days

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatiticBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar_back()

        clientes.isLoading.observe(this) {
            binding.progress.isGone = it.not()
            if (it.not()) {
                clientes.getFiltroCase.observe(this) { staticList ->
                    clientes.onStartStaticRequest(staticList)
                }
            }
        }

        clientes.isLoadingStatic.observe(this) {
            binding.progress.isGone = it
            if (it) {
                buildView()
            }
        }


    }

    //Funcion para crear la vista y la tabla de la estadistica
    @SuppressLint("InflateParams")
    private fun buildView() {
        //obtenemos los datos de la tabla con los datos
        val table_dates = StatiticProvider.initialQuantity

        //Agregar los datos a los textView
        binding.dobleCount.text = StatiticProvider.doubleQuantify.toString()
        binding.tripleCount.text = StatiticProvider.tripleQuantify.toString()

        //Construir la tabla de la cantidad de clientes por dia
        table = binding.TablaList
        table?.removeAllViews()
        for(i in (0 until table_dates.count())){
            val register = LayoutInflater.from(this).inflate(R.layout.table_static,null,false)
            val txt1 = register.findViewById<View>(R.id.day) as TextView
            val txt2 = register.findViewById<View>(R.id.day2) as TextView
            val txt3 = register.findViewById<View>(R.id.day3) as TextView
            val txt4 = register.findViewById<View>(R.id.day4) as TextView
            val txt5 = register.findViewById<View>(R.id.day5) as TextView
            val txt6 = register.findViewById<View>(R.id.day6) as TextView
            val txt7 = register.findViewById<View>(R.id.day7) as TextView
            val txt8 = register.findViewById<View>(R.id.day8) as TextView

            val count1 = register.findViewById<View>(R.id.day_count) as TextView
            val count2 = register.findViewById<View>(R.id.day_count2) as TextView
            val count3 = register.findViewById<View>(R.id.day_count3) as TextView
            val count4 = register.findViewById<View>(R.id.day_count4) as TextView
            val count5 = register.findViewById<View>(R.id.day_count5) as TextView
            val count6 = register.findViewById<View>(R.id.day_count6) as TextView
            val count7 = register.findViewById<View>(R.id.day_count7) as TextView
            val count8 = register.findViewById<View>(R.id.day_count8) as TextView

            txt1.text = dayTxt[i][0]
            txt2.text = dayTxt[i][1]
            txt3.text = dayTxt[i][2]
            txt4.text = dayTxt[i][3]
            txt5.text = dayTxt[i][4]
            txt6.text = dayTxt[i][5]
            txt7.text = dayTxt[i][6]
            txt8.text = dayTxt[i][7]

            count1.text = table_dates[i][0]
            count2.text = table_dates[i][1]
            count3.text = table_dates[i][2]
            count4.text = table_dates[i][3]
            count5.text = table_dates[i][4]
            count6.text = table_dates[i][5]
            count7.text = table_dates[i][6]
            count8.text = table_dates[i][7]

            table?.addView(register)
        }

        //Constuimos el recycleView
        binding.recyclerLugar.layoutManager = LinearLayoutManager(this)
        binding.recyclerLugar.adapter = PlaceAdapter(StatiticProvider.placeList)

        //Mostramos las vistas ocultas
        binding.txtdoble.isGone = false
        binding.dobleCount.isGone = false
        binding.txtxtriple.isGone = false
        binding.tripleCount.isGone = false
        binding.linearLayout.isGone = false
    }

    //Funcion de mensaje de alerta
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
            if (it) {
                val calendar = Calendar.getInstance()
                var month = calendar.get(Calendar.MONTH) + 1
                month = if (month == 1) {
                    12
                } else {
                    month - 1
                }
                clientes.onCreate(pos = 2, mes = month)
            } else {
                val txt = "No se pudo acceder a la base de datos. Revice su conexion a internet"
                messageAlert(txt)
                binding.progress.isGone = true
            }
        }
    }

    //Funcion para crear el toolbar en el screen
    private fun toolbar_back(){
        val back_menu = supportActionBar
        back_menu!!.title = "Regresar"
        back_menu.subtitle = "Estadistica Mensual"
        back_menu.setDisplayHomeAsUpEnabled(true)
        back_menu.setHomeAsUpIndicator(R.drawable.icon_back)
    }

    //Funcion para retornar al anterior screen de la aplicacion
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this,ConsultaActivity::class.java))
        return true
    }
}