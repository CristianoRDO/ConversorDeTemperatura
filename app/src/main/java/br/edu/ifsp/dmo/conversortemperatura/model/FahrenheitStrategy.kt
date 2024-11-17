package br.edu.ifsp.dmo.conversortemperatura.model

object FahrenheitStrategy: TemperatureConverter {

    override fun converter(temperature: Double): Double {
        return temperature
    }

    override fun getScale(): String {
        return "°F"
    }
}