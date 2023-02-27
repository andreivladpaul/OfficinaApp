package com.example.officina20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var lastBottomMenuItemSelected:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                as NavHostFragment
        val navController = navHostFragment.navController

        //aggiorna il titolo in base al fragment che si seleziona
        setupActionBarWithNavController(navController)

        //bottom menu
        val bottomNavigation:BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigation.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                as NavHostFragment
        val navController = navHostFragment.navController

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.fragment_container_view)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }


}