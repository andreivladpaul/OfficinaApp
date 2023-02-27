package com.example.officina20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)

        val startBtn = findViewById<Button>(R.id.start_btn)
        startBtn.setOnClickListener {
            beginActivity()
        }
    }

    private fun beginActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)

    }
}