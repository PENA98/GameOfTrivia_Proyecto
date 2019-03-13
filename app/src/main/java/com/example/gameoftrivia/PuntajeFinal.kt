package com.example.gameoftrivia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_puntaje_final.*

class PuntajeFinal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntaje_final)
        val objetoIntent: Intent = intent
        val nombre = objetoIntent.getStringExtra("1")
        NumeroPuntaje.text = nombre

        BtnMenu.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
