package com.example.preexamenkotlinprgmovil

import androidx.appcompat.app.AppCompatActivity

import android.app.AlertDialog

import android.content.DialogInterface

import android.content.Intent

import android.os.Bundle

import android.view.View

import android.widget.Button

import android.widget.EditText

import android.widget.RadioGroup

import android.widget.TextView

import android.widget.Toast


class ReciboNominaActivity : AppCompatActivity() {

    private lateinit var lblSubtotal: TextView
    private lateinit var lblImpuesto: TextView
    private lateinit var lblTotal: TextView

    private lateinit var numeroRecibo: EditText
    private lateinit var nombreRecibo: EditText
    private lateinit var horasTrabajadas: EditText
    private lateinit var horasExtras: EditText

    private lateinit var radioGroup: RadioGroup

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityrecibo)

        lblSubtotal = findViewById(R.id.lblSubtotal)
        lblImpuesto = findViewById(R.id.lblImpuesto)
        lblTotal = findViewById(R.id.lblTotal)

        numeroRecibo = findViewById(R.id.numeroRecibo)
        nombreRecibo = findViewById(R.id.nombreRecibo)
        horasTrabajadas = findViewById(R.id.horasTrabajadas)
        horasExtras = findViewById(R.id.horasExtras)

        radioGroup = findViewById(R.id.radioGroup)

        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)

        val textViewNombreUsuario = findViewById<TextView>(R.id.putNombreUsuario)

        val extras = intent.extras

        if (extras != null) {
            if (extras.containsKey("nombreUsuario")) {
                val nombreUsuario = extras.getString("nombreUsuario")
                textViewNombreUsuario.text = nombreUsuario
            }
        }

        btnCalcular.setOnClickListener {
            if (validarInputs()) {
                val noNomina = numeroRecibo.text.toString().trim().toInt()
                val nombreNomina = nombreRecibo.text.toString().trim()
                var puesto = ""
                val salarioBase = 200
                var salario = 0.0

                val hrsTrabajadas = horasTrabajadas.text.toString().trim().toInt()
                val horasExtrasText = horasExtras.text.toString().trim().toInt()

                val radioButtonId = radioGroup.checkedRadioButtonId

                when (radioButtonId) {
                    R.id.radioBtn1 -> {
                        puesto = "auxiliar"
                        salario = salarioBase * 1.20
                    }
                    R.id.radioBtn2 -> {
                        puesto = "Albañil"
                        salario = salarioBase * 1.50
                    }
                    R.id.radioBtn3 -> {
                        puesto = "inge"
                        salario = salarioBase * 2.0
                    }
                }

                val recibo = ReciboNomina(noNomina, nombreNomina, puesto, salario, hrsTrabajadas, horasExtrasText)

                val subtotal = recibo.GenerarSubtotal()
                val impuesto = recibo.GenerarImpuestos(subtotal)
                val totalPagar = subtotal - impuesto

                lblSubtotal.text = subtotal.toString()
                lblImpuesto.text = impuesto.toString()
                lblTotal.text = totalPagar.toString()
            }
        }

        btnLimpiar.setOnClickListener {
            numeroRecibo.setText("")
            nombreRecibo.setText("")
            horasTrabajadas.setText("")
            horasExtras.setText("")
            radioGroup.clearCheck()

            lblSubtotal.text = ""
            lblImpuesto.text = ""
            lblTotal.text = ""
        }

        btnRegresar.setOnClickListener {
            val builder = AlertDialog.Builder(this@ReciboNominaActivity)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Estás seguro de querer regresar?")
            builder.setPositiveButton("Sí") { _, _ ->
                val intent = Intent(this@ReciboNominaActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun validarInputs(): Boolean {
        val numeroReciboText = numeroRecibo.text.toString().trim()
        val nombreReciboText = nombreRecibo.text.toString().trim()
        val horasTrabajadasText = horasTrabajadas.text.toString().trim()
        val horasExtrasText = horasExtras.text.toString().trim()

        val radioButtonId = radioGroup.checkedRadioButtonId

        return if (!numeroReciboText.isEmpty() && !nombreReciboText.isEmpty() && !horasTrabajadasText.isEmpty() && !horasExtrasText.isEmpty() && radioButtonId != -1) {
            true
        } else {
            Toast.makeText(applicationContext, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
            false
        }
    }
}
