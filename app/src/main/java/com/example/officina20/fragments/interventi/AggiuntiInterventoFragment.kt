package com.example.officina20.fragments.interventi
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.officina20.R
import com.example.officina20.database.entity.Intervento
import com.example.officina20.viewmodel.InterventoViewModel
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class AggiuntiInterventoFragment : Fragment() {
    private lateinit var interventoViewModel:InterventoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_aggiunti_intervento, container, false)

        interventoViewModel = ViewModelProvider(this).get(InterventoViewModel::class.java)

        view.findViewById<Button>(R.id.add_intervention_to_db_btn).setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val descrizione = view?.findViewById<EditText>(R.id.descrizione)?.text.toString()
        val durata = view?.findViewById<EditText>(R.id.durata)?.text.toString()
        val dataArrivo = view?.findViewById<EditText>(R.id.data_arrivo)?.text.toString()
        val dataConsegna = view?.findViewById<EditText>(R.id.data_consegna)?.text.toString()
        val autoId = view?.findViewById<EditText>(R.id.auto_id)?.text.toString()


        if (inputCheck(descrizione,durata,dataArrivo,dataConsegna,autoId)){

            val intervento = Intervento(0,descrizione,durata.toFloat(),dataArrivo,dataConsegna,autoId.toInt())
            interventoViewModel.addIntervention(intervento)
            MotionToast.darkToast(requireActivity(),
                "Aggiunta avvenuta con successo!",
                "Intervento aggiunto",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireActivity(), www.sanju.motiontoast.R.font.helvetica_regular))


            findNavController().navigate(R.id.action_aggiuntiInterventoFragment_to_listaClientiFragment)
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
    private fun inputCheck(descrizione: String,durata:String,dataArrivo:String,dataConsegna:String,autoId:String): Boolean {
        return!(descrizione.isEmpty() && durata.isEmpty() && dataArrivo.isEmpty() &&
                dataConsegna.isEmpty() && autoId.isEmpty())
    }

}