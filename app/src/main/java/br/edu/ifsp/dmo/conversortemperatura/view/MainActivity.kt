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
import br.edu.ifsp.dmo.conversortemperatura.model.StaticConverters
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var converter: TemperatureConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
        setContentSpinners()
    }

    private fun setClickListener()
    {
        binding.btnConverter.setOnClickListener{
            val toScale = getSelectedToScale()

            when(toScale.lowercase()) {
                "celsius" -> handleConversion(CelsiusStrategy)
                "fahrenheit" -> handleConversion(FahrenheitStrategy)
                "kelvin" -> handleConversion(KelvinStrategy)
                else -> throw IllegalArgumentException(getString(R.string.input_error))
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

    private fun handleConversion(strategy: TemperatureConverter)
    {
        converter = strategy

        try{
            val fromScale = getSelectedFromScale()
            val temperature = transformTemperatureForFahrenheit(fromScale, getTemperature())

            binding.textviewResultNumber.text = displayTemperature(temperature)
            binding.textviewTextResult.text = displayConversionMessage(fromScale)

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

    private fun transformTemperatureForFahrenheit(fromScale: String, temperature: Double): Double
    {
        return when(fromScale.lowercase())
        {
            "celsius" -> StaticConverters.celsiusToFahrenheit(temperature)
            "kelvin" -> StaticConverters.kelvinToFahrenheit(temperature)
            "fahrenheit" -> temperature
            else -> throw IllegalArgumentException(getString(R.string.input_error))
        }
    }

    private fun displayTemperature(temperature: Double): String
    {
        return String.format(Locale.getDefault(), "%.2f %s", converter.converter(temperature), converter.getScale())
    }

    private fun displayConversionMessage(fromScale: String): String
    {
        return when(converter) {
            CelsiusStrategy -> "${getString(R.string.msgConversion)} $fromScale ${getString(R.string.msgConversion2)} ${getString(R.string.celsius)}"
            FahrenheitStrategy -> "${getString(R.string.msgConversion)} $fromScale ${getString(R.string.msgConversion2)} ${getString(R.string.fahrenheit)}"
            KelvinStrategy -> "${getString(R.string.msgConversion)} $fromScale ${getString(R.string.msgConversion2)} ${getString(R.string.kelvin)}"
            else -> throw IllegalArgumentException(getString(R.string.input_error))
        }
    }


}