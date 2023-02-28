package com.example.officina20.fragments.clienti

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.officina20.R
import com.example.officina20.fragments.auto.AutoClienteFragment
//import com.example.officina20.fragments.auto.AutoClienteFragment
import com.example.officina20.interfaces.OnItemClickListener
import com.example.officina20.viewmodel.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaClientiFragment : Fragment(), OnItemClickListener {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_clienti, container, false)

        //Recycler
        val adapter = ListAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_clienti)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner) { user ->
            adapter.setData(user)
        }

        //Round floating button
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.VISIBLE
        return view
    }


    override fun onItemClick(position: Int) {

        val selectedItem = userViewModel.onRecyclerViewClick(position)
        val bundle = Bundle().apply {
            putInt("clienteId", selectedItem)
        }
        val autoCliente = AutoClienteFragment()
        autoCliente.arguments = bundle
        // Avvia il secondo Fragment
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, autoCliente)
            .addToBackStack(null)
            .commit()
    }

}