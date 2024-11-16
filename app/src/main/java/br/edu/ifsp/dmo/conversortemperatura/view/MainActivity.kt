package br.edu.ifsp.dmo.conversortemperatura.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
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
            val fromScale = getSelectedFromScale()
            val toScale = getSelectedToScale()

            when(fromScale.lowercase()) {
                "celsius" -> handleConversion(CelsiusStrategy, toScale)
                "fahrenheit" -> handleConversion(FahrenheitStrategy, toScale)
                "kelvin" -> handleConversion(KelvinStrategy, toScale)
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
            throw NumberFormatException(getString(R.string.input_error))
        }
    }

    private fun handleConversion(strategy: TemperatureConverter, toScale: String)
    {
        converter = strategy

        try{
            val temperature = getTemperature()

            when(toScale.lowercase()) {
                "celsius" -> binding.textviewResultNumber.text = String.format("%.2f %s", converter.converterParaCelsius(temperature), getScaleConversion(toScale))
                "fahrenheit" -> binding.textviewResultNumber.text = String.format("%.2f %s", converter.converterParaFahrenheit(temperature), getScaleConversion(toScale))
                "kelvin" -> binding.textviewResultNumber.text = String.format("%.2f %s", converter.converterParaKelvin(temperature), getScaleConversion(toScale))
            }

            when(converter)
            {
                CelsiusStrategy -> binding.textviewTextResult.text = "${getString(R.string.msgConversion)} ${getString(R.string.celsius)} ${getString(R.string.msgConversion2)} $toScale "
            }
        }
        catch (ex: NumberFormatException)
        {
            Toast.makeText(this, getString(R.string.error_popup_notify), Toast.LENGTH_LONG).show()
        }
    }

    private fun setContentSpinners()
    {
        val stringArray = resources.getStringArray(R.array.spinner_scales)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArray)

        binding.spinnerConvertFrom.adapter = adapter
        binding.spinnerConvertTo.adapter = adapter
    }

    private fun getSelectedFromScale(): String
    {
        return binding.spinnerConvertFrom.selectedItem.toString()
    }

    private fun getSelectedToScale(): String
    {
        return binding.spinnerConvertTo.selectedItem.toString()
    }

    private fun getScaleConversion(toScale: String): String
    {
        return when(toScale.lowercase())
            {
                "celsius" -> CelsiusStrategy.getScale()
                "fahrenheit" -> FahrenheitStrategy.getScale()
                "kelvin" -> KelvinStrategy.getScale()
                else -> throw IllegalArgumentException()
            }
    }

}