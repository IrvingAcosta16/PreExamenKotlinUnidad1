package com.example.preexamenkotlinprgmovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog


import android.content.Intent

import android.widget.Button

import android.widget.EditText

import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputUsuario = findViewById<EditText>(R.id.inputUsuario)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        btnEntrar.setOnClickListener {
            val nombreUsuario = inputUsuario.text.toString().trim()

            if (nombreUsuario.isEmpty()) {
                Toast.makeText(applicationContext, "Ingrese un nombre de usuario", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle()
                bundle.putString("nombreUsuario", nombreUsuario)

                Toast.makeText(applicationContext, "Bienvenido", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity, ReciboNominaActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

        btnSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Estás seguro de querer salir?")
            builder.setPositiveButton("Sí") { _, _ ->
                finishAffinity()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

}