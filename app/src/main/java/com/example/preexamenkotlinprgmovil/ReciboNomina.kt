package com.example.preexamenkotlinprgmovil

class ReciboNomina {
    private var noNomina: Int
    private var nombreNomina: String
    private var puesto: String
    private var salario: Double
    private var horasTrab: Int
    private var horasExtraTrab: Int
    private var subtotal = 0.0

    internal constructor() {
        noNomina = 0
        nombreNomina = ""
        puesto = ""
        salario = 0.0
        horasTrab = 0
        horasExtraTrab = 0
    }

    internal constructor(
        noNomina: Int,
        nombreNomina: String,
        puesto: String,
        salario: Double,
        horasTrab: Int,
        horasExtraTrab: Int
    ) {
        this.noNomina = noNomina
        this.nombreNomina = nombreNomina
        this.puesto = puesto
        this.salario = salario
        this.horasTrab = horasTrab
        this.horasExtraTrab = horasExtraTrab
    }

    fun GenerarSubtotal(): Double {
        subtotal = salario * horasTrab + salario * horasExtraTrab * 2
        return subtotal
    }

    fun GenerarImpuestos(salario: Double): Double {
        return salario * 0.16
    }
}
