package br.edu.ifsp.dmo.conversortemperatura.model

interface TemperatureConverter {

    fun converter(temperature: Double): Double;
    fun getScale(): String;

}