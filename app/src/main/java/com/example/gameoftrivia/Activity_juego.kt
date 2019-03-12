package com.example.gameoftrivia

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RadioButton
import android.widget.TextView
import com.google.gson.Gson
import java.io.FileInputStream
import java.io.IOException
import com.google.gson.reflect.TypeToken




class Activity_juego : AppCompatActivity() {

    private var opcion:String? = null
    var Pregunta : TextView? = null
    var rbResp1 : RadioButton? = null
    var rbResp2 : RadioButton? = null
    var rbResp3 : RadioButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avtivity_juego)

        val objetoIntent: Intent=intent
        val nombre = objetoIntent.getStringExtra("1")
        opcion = nombre

        if(opcion == "facil"){

            val contenidoArchivo : String = ReadDataFromFile("facil.json");
                Log.d("resultado", contenidoArchivo)
                jsonToObject(contenidoArchivo)
        }
    }

    fun ReadDataFromFile(file: String): String {
        var data = ""

        try {
            val stream = assets.open(file)

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            data = String(buffer)
        } catch (e: IOException) {
            // Handle exceptions here
        }

        return data
    }

    fun jsonToObject(setPreguntas: String) {
        // Inicializar un objeto de tipo Gson
        val PreguntasListType = object : TypeToken<ArrayList<Preguntas>>() {}.javaClass
        val gson = Gson()
        val parseado = gson.fromJson(setPreguntas, PreguntasListType)
        // Mapear las variables a las vistas del layout
        Pregunta = findViewById(R.id.tvPregunta)
        rbResp1 = findViewById(R.id.rbResp1)
        rbResp2 = findViewById(R.id.rbResp2)
        rbResp3 = findViewById(R.id.rbResp3)

        // Asignar los valores obtenidos a los radio buttons y el text view
        Pregunta?.text = "${parseado.rawType}"


    }

}
