package com.example.gameoftrivia

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_avtivity_juego.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.io.IOException
import kotlin.random.Random


class ActivityJuego : AppCompatActivity() {

    private var opcion:String? = null
    private var Pregunta : TextView? = null
    private var rbResp1 : RadioButton? = null
    private var rbResp2 : RadioButton? = null
    private var rbResp3 : RadioButton? = null
    private var parseado : PreguntasListas? = null
    private var count : Int = 0
    private var condicion : Int = 0
    private var numeroPreguntas : Int = 0
    private var puntaje : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avtivity_juego)

        val objetoIntent: Intent = intent
        val nombre = objetoIntent.getStringExtra("1")
        opcion = nombre
        when (opcion) {
            "facil.json" -> numeroPreguntas = 10
            "medio.json" -> numeroPreguntas = 15
            "dificil.json" -> numeroPreguntas = 20
        }
        val contenidoArchivo : String = ReadDataFromFile(opcion.toString())
        Log.d("resultado", contenidoArchivo)
        jsonToObject(contenidoArchivo)
        rbResp1!!.setOnClickListener{
            rbResp1!!.isChecked = true
            if (rbResp1!!.isChecked)
                Toast.makeText(this, "Opcion 1 seleccionada", Toast.LENGTH_SHORT ).show()
        }
        rbResp2!!.setOnClickListener{
            rbResp2!!.isChecked = true
            if (rbResp2!!.isChecked)
                Toast.makeText(this, "Opcion 2 seleccionada", Toast.LENGTH_SHORT ).show()
        }
        rbResp3!!.setOnClickListener{
            rbResp3!!.isChecked = true
            if (rbResp3!!.isChecked)
                Toast.makeText(this, "Opcion 3 seleccionada", Toast.LENGTH_SHORT ).show()
        }
        BtnResponder.setOnClickListener{
            Toast.makeText(this, "Respuesta", Toast.LENGTH_SHORT).show()
            comprobarRespuesta()
            if (count >= numeroPreguntas){
                val intent = Intent(this, PuntajeFinal::class.java)
                intent.putExtra("1", puntaje.toString())
                startActivity(intent)
                finish()
            }

        }


    }

    override fun onStart() {
        super.onStart()
        doAsync {
            jugar()
            uiThread {
                toast("Termina ciclo del juego")
            }
        }
    }

    private fun jugar()
    {
        //Ciclo que controla el juego
        do {
            if (condicion == 0){
                asignarValor(count)
                condicion += 1
            }
        }while (count <= numeroPreguntas)

    }

    //Funcion sacada de StackOverFlow
    // Cumple la funcion de leer los archivos Json desde la carpeta assets
    private fun ReadDataFromFile(file: String): String {
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

    // Parseamos los archivos Json a objetos Kotlin
    fun jsonToObject(setPreguntas: String) {
        // Inicializar un objeto de tipo Gson
        //val PreguntasListType  = object : TypeToken<ArrayList<PreguntasListas>>() {}.javaClass
        val gson = Gson()

        parseado = gson.fromJson(setPreguntas, PreguntasListas::class.java)
        // Mapear las variables a las vistas del layout
        Pregunta = findViewById(R.id.tvPregunta)
        rbResp1 = findViewById(R.id.rbResp1)
        rbResp2 = findViewById(R.id.rbResp2)
        rbResp3 = findViewById(R.id.rbResp3)

    }

    // Asignamos el valor a cada uno de los controles
    private fun asignarValor(i:Int)
    {
        var ran = Random.nextInt((100 - 0))
        Pregunta?.text = null
        rbResp1?.text = null
        rbResp2?.text = null
        rbResp3?.text = null
        when (ran) {
            in 0..32 -> {
                Pregunta?.text = parseado!!.preguntas[i].pregunta
                rbResp1?.text = parseado!!.preguntas[i].mala1
                rbResp2?.text = parseado!!.preguntas[i].mala2
                rbResp3?.text = parseado!!.preguntas[i].correcta
            }
            in 33..65 -> {
                Pregunta?.text = parseado!!.preguntas[i].pregunta
                rbResp1?.text = parseado!!.preguntas[i].mala2
                rbResp2?.text = parseado!!.preguntas[i].correcta
                rbResp3?.text = parseado!!.preguntas[i].mala1
            }
            in 66..100 -> {
                Pregunta?.text = parseado!!.preguntas[i].pregunta
                rbResp1?.text = parseado!!.preguntas[i].correcta
                rbResp2?.text = parseado!!.preguntas[i].mala1
                rbResp3?.text = parseado!!.preguntas[i].mala2
            }
        }
    }

    //Esta funcion se encarga de sumar los puntos si la respuesta es correcta y de reiniciar los radioButton
    private fun comprobarRespuesta(){
        if(rbResp1!!.isChecked && rbResp1!!.text == parseado!!.preguntas[count].correcta) {
            //Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()
            count += 1
            condicion = 0
            puntaje += 1
            rbResp1!!.isChecked = false
        }else if(rbResp2!!.isChecked && rbResp2!!.text == parseado!!.preguntas[count].correcta){
            //Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()
            count += 1
            condicion = 0
            rbResp2!!.isChecked = false
            puntaje += 1
        } else if(rbResp3!!.isChecked && rbResp3!!.text == parseado!!.preguntas[count].correcta){
            //Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()
            count += 1
            condicion = 0
            rbResp3!!.isChecked = false
            puntaje += 1
        }else{
            count += 1
            rbResp1!!.isChecked = false
            rbResp2!!.isChecked = false
            rbResp3!!.isChecked = false
            condicion = 0
        }

    }

}
