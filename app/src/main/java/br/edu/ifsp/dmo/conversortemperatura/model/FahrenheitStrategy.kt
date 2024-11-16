package br.edu.ifsp.dmo.conversortemperatura.model

object FahrenheitStrategy: TemperatureConverter {

    override fun converterParaCelsius(temperature: Double): Double {
        return (temperature - 32) * 5 / 9
    }

    override fun converterParaFahrenheit(temperature: Double): Double {
        return temperature
    }

    override fun converterParaKelvin(temperature: Double): Double {
        return converterParaCelsius(temperature) + 273.15
    }

    override fun getScale(): String {
        return "°F"
    }
}