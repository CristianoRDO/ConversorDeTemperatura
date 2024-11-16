package br.edu.ifsp.dmo.conversortemperatura.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.dmo.conversortemperatura.R
import br.edu.ifsp.dmo.conversortemperatura.model.TemperatureConverter
import br.edu.ifsp.dmo.conversortemperatura.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.conversortemperatura.model.CelsiusStrategy
import br.edu.ifsp.dmo.conversortemperatura.model.FahrenheitStrategy
import br.edu.ifsp.dmo.conversortemperatura.model.KelvinStrategy

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var converter: TemperatureConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener();
        setContentSpinners();

    }

    private fun setClickListener()
    {
        binding.btnConverter.setOnClickListener(){
            val scaleFrom = getScaleFrom();

            when(scaleFrom) {
                "Celsius" -> handleConversion(CelsiusStrategy)
                "Fahrenheit" -> handleConversion(FahrenheitStrategy)
                "Kelvin" -> handleConversion(KelvinStrategy)
            }
        }
    }

    private fun getTemperature(): Double
    {
        return try{
            binding.edittextTemperature.text.toString().toDouble()
        }
        catch (ex: NumberFormatException)
        {
            throw NumberFormatException("Input Error")
        }
    }

    private fun handleConversion(strategy: TemperatureConverter)
    {
        converter = strategy

        val temperature = getTemperature()
        val scaleTo = getScaleTo()

        when(scaleTo) {
            "Celsius" -> binding.textviewResultNumber.text = String.format("%.2f %s", converter.converterParaCelsius(temperature), converter.getScale())
            "Fahrenheit" -> binding.textviewResultNumber.text = String.format("%.2f %s", converter.converterParaFahrenheit(temperature), converter.getScale())
            "Kelvin" -> binding.textviewResultNumber.text = String.format("%.2f %s", converter.converterParaKelvin(temperature), converter.getScale())
        }

    }

    private fun setContentSpinners()
    {
        val stringArray = resources.getStringArray(R.array.spinner_scales);

        binding.spinnerConvertFrom.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArray)
        binding.spinnerConvertTo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArray)
    }

    private fun getScaleFrom(): String
    {
        return binding.spinnerConvertFrom.selectedItem.toString()
    }

    private fun getScaleTo(): String
    {
        return binding.spinnerConvertTo.selectedItem.toString()
    }


}