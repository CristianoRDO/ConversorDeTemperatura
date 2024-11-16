package br.edu.ifsp.dmo.conversortemperatura.model

object CelsiusStrategy: TemperatureConverter {

    override fun converterParaCelsius(temperature: Double): Double {
        return temperature;
    }

    override fun converterParaFahrenheit(temperature: Double): Double {
        return 1.8 * temperature + 32;
    }

    override fun converterParaKelvin(temperature: Double): Double {
        return temperature + 273.15;
    }

    override fun getScale(): String {
        return "°C";
    }
}