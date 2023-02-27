package com.example.officina20.fragments.clienti

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
import com.example.officina20.database.entity.Cliente
import com.example.officina20.viewmodel.UserViewModel
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.util.*


class AggiungiClienteFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_aggiungi_cliente, container, false)
        view.findViewById<Button>(R.id.add_to_db_btn).setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val nome = view?.findViewById<EditText>(R.id.name)?.text.toString()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        val cognome = view?.findViewById<EditText>(R.id.surname)?.text.toString()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        val email = view?.findViewById<EditText>(R.id.email)?.text.toString()


        if (inputCheck(nome,cognome,email)){
            //crea un nuovo utente
            val cliente = Cliente(0,nome,cognome,email)
            userViewModel.addUser(cliente)
            MotionToast.darkToast(requireActivity(),
                "Aggiunta avvenuta con successo!",
                "Cliente aggiunto",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireActivity(), www.sanju.motiontoast.R.font.helvetica_regular))
            //dopo aver aggiunto l'utente ritorniamo alla list user
            findNavController().navigate(R.id.action_aggiungiClienteFragment_to_listaClientiFragment)
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
    private fun inputCheck(firstName: String,lastName:String,email:String): Boolean {
        return!(firstName.isEmpty() && lastName.isEmpty() &&
                email.isEmpty())
    }

}

