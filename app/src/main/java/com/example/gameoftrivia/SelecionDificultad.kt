package com.example.gameoftrivia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_selecion_dificultad.*

class SelecionDificultad : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecion_dificultad)

        BtnFacil.setOnClickListener{
            //llamamos al activity
            val intent = Intent(this, ActivityJuego::class.java)
            intent.putExtra("1", "facil.json")
            startActivity(intent)
            finish()
        }
        BtnMedia.setOnClickListener{
            //llamamos al activity
            val intent = Intent(this, ActivityJuego::class.java)
            intent.putExtra("1", "medio.json")
            startActivity(intent)
            finish()
        }
        BtnDificil.setOnClickListener{
            //llamamos al activity
            val intent = Intent(this, ActivityJuego::class.java)
            intent.putExtra("1", "dificil.json")
            startActivity(intent)
            finish()
        }
        BtnVolver.setOnClickListener{
            //terminamos el activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
