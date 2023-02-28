package com.example.officina20.fragments.auto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.officina20.R
import com.example.officina20.database.OfficinaDatabase
import com.example.officina20.database.dao.DaoAuto
import com.example.officina20.database.entity.Auto
import com.example.officina20.fragments.clienti.ListaClientiFragment
import com.example.officina20.fragments.interventi.DettagliInterventiFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AutoClienteFragment : Fragment() {
    private var mBundle: Bundle? = null
    private lateinit var autoClienteDao: DaoAuto
    private lateinit var auto:Auto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = OfficinaDatabase.getDatabase(requireContext())
        autoClienteDao = db.autoDao()

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_auto_cliente, container, false)


        mBundle = arguments
        val clienteId = arguments?.getInt("clienteId")

        lifecycleScope.launch(Dispatchers.IO){

            if(autoClienteDao.getAutoByClientId(clienteId) != null){

                    auto = autoClienteDao.getAutoByClientId(clienteId)

                    view.findViewById<TextView>(R.id.marca_tv).text = auto.marca
                    view.findViewById<TextView>(R.id.modello_tv).text = auto.modello
                    view.findViewById<TextView>(R.id.telaio_tv).text = auto.nTelaio
                    view.findViewById<TextView>(R.id.targa_tv).text = auto.targa

                }else {
                val snackbar =
                    Snackbar.make(view, "Non ci sono auto inserite", Snackbar.LENGTH_LONG)
                snackbar.show()
                parentFragmentManager.popBackStack()

            }

        }
        //passa l'id della macchina
        view.findViewById<Button>(R.id.intervento).setOnClickListener {
            val bundle = Bundle().apply {
                putInt("autoId", auto.idAuto)
            }
            val dettagliInterventoFragment = DettagliInterventiFragment()
            dettagliInterventoFragment.arguments = bundle
            // Avvia il secondo Fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, dettagliInterventoFragment)
                .addToBackStack(null)
                .commit()

        }

        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.GONE
        return view
    }


    fun getClientId():Int? {
        return mBundle?.getInt("clienteId")
    }

}
