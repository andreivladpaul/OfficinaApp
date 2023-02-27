package com.example.officina20.fragments.clienti

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.officina20.R
import com.example.officina20.database.entity.Cliente
import com.example.officina20.interfaces.OnItemClickListener


//class che ha la funzione di adapter per il recycler view
class ListAdapter(private var listener: OnItemClickListener) : RecyclerView.Adapter
<ListAdapter.MyViewHolder>() {

    private var clientList = emptyList<Cliente>()

    //gestore della view(layout)
    inner class MyViewHolder(itemView: View, private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false),
            listener
        )
    }

    override fun getItemCount(): Int = clientList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = clientList[position]

        holder.itemView.findViewById<TextView>(R.id.id_txt).text = currentItem.clienteId.toString()
        holder.itemView.findViewById<TextView>(R.id.name_txt).text = currentItem.nome + " " + currentItem.cognome
        holder.itemView.findViewById<TextView>(R.id.email_txt).text = currentItem.email
        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem.clienteId)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(client: List<Cliente>) {
        this.clientList = client
        notifyDataSetChanged()
    }
}

