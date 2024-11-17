package br.edu.ifsp.dmo.conversortemperatura.model

object StaticConverters {

    fun celsiusToFahrenheit(temperature: Double): Double {
        return 1.8 * temperature + 32
    }

    fun kelvinToFahrenheit(temperature: Double): Double {
        return (temperature - 273.15) * 9 / 5 + 32
    }

}