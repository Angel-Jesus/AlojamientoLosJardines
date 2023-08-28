package com.example.alojamientolosjardines.data.recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alojamientolosjardines.R
import com.example.alojamientolosjardines.data.model.ClienteModel

class ClientesAdapter(private val List_clientes:ArrayList<ClienteModel>, private val onClickListener:(Array<String>, Int) -> Unit): RecyclerView.Adapter<ClientesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ClientesViewHolder(layoutInflater.inflate(R.layout.item_clientes,parent,false))
    }

    override fun onBindViewHolder(holder: ClientesViewHolder, position: Int) {
        val item = List_clientes[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = List_clientes.size
}