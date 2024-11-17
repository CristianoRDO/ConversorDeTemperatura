package br.edu.ifsp.dmo.conversortemperatura.model

object CelsiusStrategy: TemperatureConverter {

    override fun converter(temperature: Double): Double {
        return (temperature - 32) * 5 / 9;
    }

    override fun getScale(): String {
        return "°C";
    }
}