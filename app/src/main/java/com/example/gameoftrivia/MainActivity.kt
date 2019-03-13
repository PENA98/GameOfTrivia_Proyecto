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
            // Mandamos a llamar el siguiente activity
            //Para ello usamos Intent
            val intent = Intent(this, SelecionDificultad::class.java)
            startActivity(intent)
            finish()
        }

        BtnSalir.setOnClickListener {
            finish()
        }
    }

}

