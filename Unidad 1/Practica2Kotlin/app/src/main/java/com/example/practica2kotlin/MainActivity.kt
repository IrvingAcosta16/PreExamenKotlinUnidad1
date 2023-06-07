package com.example.practica2kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var editTextWeight: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
        editTextWeight = findViewById(R.id.editTextWeight)
        editTextHeight = findViewById(R.id.editTextHeight)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        textViewResult = findViewById(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            calculateBMI()
        }

        btnLimpiar.setOnClickListener {
            Limpiar()
        }

        btnCerrar.setOnClickListener {
            finish()
        }
    }

    private fun calculateBMI() {
        val weightStr = editTextWeight.text.toString()
        val heightStr = editTextHeight.text.toString()

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            textViewResult.text = "Por favor inserte Peso y Altura"
            return
        }

        val weight = weightStr.toFloat()
        val height = heightStr.toFloat()

        val bmi = weight / (height * height)

        val result = when {
            bmi < 18.5 -> "Bajo de Peso"
            bmi < 25 -> "Peso Normal"
            bmi < 30 -> "Sobrepeso"
            else -> "Obesidad"
        }

        val bmiResult = "IMC: ${"%.2f".format(bmi)}\n$result"
        textViewResult.text = bmiResult
    }

    private fun Limpiar() {
        editTextWeight.text.clear()
        editTextHeight.text.clear()
        textViewResult.text = ""
    }
}