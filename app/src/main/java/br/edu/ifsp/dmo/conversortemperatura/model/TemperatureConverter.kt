package br.edu.ifsp.dmo.conversortemperatura.model

interface TemperatureConverter {

    fun converterParaCelsius(temperature: Double): Double;
    fun converterParaFahrenheit(temperature: Double): Double;
    fun converterParaKelvin(temperature: Double): Double;
    fun getScale(): String;

}