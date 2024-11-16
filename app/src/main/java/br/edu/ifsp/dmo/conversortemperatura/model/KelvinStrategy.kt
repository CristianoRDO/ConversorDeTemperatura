package br.edu.ifsp.dmo.conversortemperatura.model

object KelvinStrategy: TemperatureConverter {

    override fun converterParaCelsius(temperature: Double): Double {
        return temperature - 273.15;
    }

    override fun converterParaFahrenheit(temperature: Double): Double {
        return converterParaCelsius(temperature) * 1.8 + 32;
    }

    override fun converterParaKelvin(temperature: Double): Double {
        return temperature;
    }

    override fun getScale(): String {
        return "K";
    }

}