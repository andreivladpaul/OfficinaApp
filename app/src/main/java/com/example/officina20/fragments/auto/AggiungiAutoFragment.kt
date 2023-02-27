package com.example.officina20.fragments.auto

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.officina20.R
import com.example.officina20.database.entity.Auto
import com.example.officina20.database.entity.Cliente
import com.example.officina20.viewmodel.AutoViewModel
import com.example.officina20.viewmodel.UserViewModel
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class AggiungiAutoFragment : Fragment() {
    lateinit var autoViewModel: AutoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_auto, container, false)

        autoViewModel = ViewModelProvider(this).get(AutoViewModel::class.java)

        view.findViewById<Button>(R.id.add_auto_to_db_btn).setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }
    private fun insertDataToDatabase() {
        val marca = view?.findViewById<EditText>(R.id.marca)?.text.toString()
        val modello = view?.findViewById<EditText>(R.id.modello)?.text.toString()
        val nrTelaio = view?.findViewById<EditText>(R.id.nr_telaio)?.text.toString()
        val targa = view?.findViewById<EditText>(R.id.targa)?.text.toString()
        val idCliente = view?.findViewById<EditText>(R.id.numero_cliente)?.text.toString()


        if (inputCheck(marca,modello,nrTelaio,targa,idCliente)){
            //crea un nuova auto
            val auto = Auto(0,nrTelaio,marca,modello,targa,idCliente.toInt())

            autoViewModel.addAuto(auto)

            MotionToast.darkToast(requireActivity(),
                "Aggiunta avvenuta con successo!",
                "Auto aggiunta",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireActivity(), www.sanju.motiontoast.R.font.helvetica_regular))
            //dopo aver aggiunto l'auto ritorniamo all'elenco delle auto
            findNavController().navigate(R.id.action_aggiungiAutoFragment_to_listaClientiFragment)
        } else {
            MotionToast.darkToast(requireActivity(),
                "Attenzione!",
                "Dati Incompleti",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireActivity(), www.sanju.motiontoast.R.font.helvetica_regular))
        }

    }
    private fun inputCheck(marca: String,modello:String,nrTelaio:String,targa:String,idCliente:String): Boolean {
        return!(marca.isEmpty() && modello.isEmpty() && nrTelaio.isEmpty() &&
                targa.isEmpty() && idCliente.isEmpty())
    }
}