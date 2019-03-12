package com.example.gameoftrivia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_selecion_dificultad.*

class selecion_dificultad : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecion_dificultad)

        BtnFacil.setOnClickListener{
            val intent = Intent(this, Activity_juego::class.java)
            intent.putExtra("1", "facil.json")
            startActivity(intent)
        }
        BtnMedia.setOnClickListener{
            val intent = Intent(this, Activity_juego::class.java)
            intent.putExtra("1", "medio.json")
            startActivity(intent)
        }
        BtnDificil.setOnClickListener{
            val intent = Intent(this, Activity_juego::class.java)
            intent.putExtra("1", "dificil.json")
            startActivity(intent)
        }

    }
}
