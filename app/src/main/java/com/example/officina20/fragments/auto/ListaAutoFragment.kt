package com.example.officina20.fragments.auto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.officina20.R
import com.example.officina20.fragments.clienti.ListAdapter
import com.example.officina20.fragments.interventi.DettagliInterventiFragment
import com.example.officina20.interfaces.OnItemClickListener
import com.example.officina20.viewmodel.AutoViewModel
import com.example.officina20.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * A simple [Fragment] subclass.
 * Use the [ListaAutoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaAutoFragment : Fragment(), OnItemClickListener{
    private lateinit var autoViewModel: AutoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\

        val view = inflater.inflate(R.layout.fragment_lista_auto, container, false)

        val adapter = ListAutoAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_auto)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ViewModel
        autoViewModel = ViewModelProvider(this).get(AutoViewModel::class.java)
        autoViewModel.readAllData.observe(viewLifecycleOwner) { auto ->
            adapter.setData(auto)
        }

        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonAuto).setOnClickListener {
            findNavController().navigate(R.id.action_listaAutoFragment_to_aggiungiAutoFragment)
        }

        return view
    }


    override fun onItemClick(position: Int) {

        val selectedItem = autoViewModel.onRecyclerViewClick(position)
        val bundle = Bundle().apply {
            putInt("autoId", selectedItem)
        }
        val dettagliInterventoFragment = DettagliInterventiFragment()
        dettagliInterventoFragment.arguments = bundle
        Log.d("ID INTERVENTO DA LISTA AUTO","$selectedItem")
        // Avvia il secondo Fragment

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, dettagliInterventoFragment)
            .addToBackStack(null)
            .commit()

    }

}