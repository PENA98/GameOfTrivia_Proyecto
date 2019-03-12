package com.example.gameoftrivia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BtnJugar.setOnClickListener{
            val intent = Intent(this, selecion_dificultad::class.java)
            startActivity(intent)
        }

        BtnSalir.setOnClickListener {
            finish()
        }
    }

}

