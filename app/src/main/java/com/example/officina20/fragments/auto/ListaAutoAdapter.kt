package com.example.officina20.fragments.auto

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.officina20.R
import com.example.officina20.database.entity.Auto
import com.example.officina20.database.entity.Cliente
import com.example.officina20.interfaces.OnItemClickListener


//class che ha la funzione di adapter per il recycler view
class ListAutoAdapter(private var listener: OnItemClickListener): RecyclerView.Adapter<ListAutoAdapter.MyViewHolder>() {

    private var autoList = emptyList<Auto>()
    //gestore della view(layout)
    inner class MyViewHolder(itemView:View,  private val listener: OnItemClickListener)
        :RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.auto_custom_row,parent,false), listener)
    }
    override fun getItemCount(): Int = autoList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = autoList[position]

        holder.itemView.findViewById<TextView>(R.id.marca_txt)?.text = currentItem.marca
        holder.itemView.findViewById<TextView>(R.id.modello_txt)?.text = currentItem.modello
        holder.itemView.findViewById<TextView>(R.id.targa_txt)?.text = currentItem.targa
        holder.itemView.findViewById<TextView>(R.id.nr_telaio_txt)?.text = currentItem.nTelaio

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem.idAuto)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(auto: List<Auto>){
        this.autoList = auto
        notifyDataSetChanged()
    }

}



