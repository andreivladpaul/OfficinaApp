package com.example.officina20.fragments.interventi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.officina20.R
import com.example.officina20.database.OfficinaDatabase
import com.example.officina20.database.dao.DaoAuto
import com.example.officina20.database.dao.DaoInterventi
import com.example.officina20.fragments.clienti.ListaClientiFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DettagliInterventiFragment : Fragment() {
    private var mBundle: Bundle? = null
    private lateinit var interventoDao: DaoInterventi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = OfficinaDatabase.getDatabase(requireContext())
        interventoDao = db.interventoDao()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBundle = arguments
        val autoId = arguments?.getInt("autoId")

        val view =inflater.inflate(R.layout.fragment_dettagli_interventi, container, false)



        lifecycleScope.launch(Dispatchers.IO){

            try {
                val intervento = interventoDao.getInterventionByPlate(autoId!!)
                Log.d("Intervento","$intervento")

                view.findViewById<TextView>(R.id.descrizione).text = intervento.descrizione
                view.findViewById<TextView>(R.id.durata).text = intervento.durata.toString()
                view.findViewById<TextView>(R.id.data_consegna).text = intervento.dataConsegna

            }catch (e: java.lang.NullPointerException) {
                /*
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, ListaClientiFragment())
                    .addToBackStack(null)
                    .commit()

                 */
                val snackbar =
                    Snackbar.make(view, "Non ci sono interventi inseriti", Snackbar.LENGTH_LONG)
                snackbar.show()
                parentFragmentManager.popBackStack()
            }
        }
        return view
    }

}