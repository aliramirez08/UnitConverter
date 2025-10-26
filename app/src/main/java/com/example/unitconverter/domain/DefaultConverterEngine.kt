package com.example.unitconverter.domain

import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.round

/**
 * Concrete implementation of [ConverterEngine] used in the app.
 * Input is a String (user input), output is a formatted String (e.g., "32.00").
 */
class DefaultConverterEngine @Inject constructor() : ConverterEngine {

    override fun convert(category: String, from: String, to: String, value: String): String {
        val input = value.toDoubleOrNull() ?: return ""

        val result = when (category) {
            "Temperature" -> convertTemperature(input, from, to)
            "Length"      -> convertLength(input, from, to)
            "Weight"      -> convertWeight(input, from, to)
            else          -> input
        }

        return format(result)
    }

    private fun convertTemperature(v: Double, from: String, to: String): Double {
        // Normalize to Celsius
        val celsius = when (from) {
            "Celsius"    -> v
            "Fahrenheit" -> (v - 32.0) * 5.0 / 9.0
            "Kelvin"     -> v - 273.15
            else         -> v
        }
        // Convert from Celsius to target
        return when (to) {
            "Celsius"    -> celsius
            "Fahrenheit" -> celsius * 9.0 / 5.0 + 32.0
            "Kelvin"     -> celsius + 273.15
            else         -> v
        }
    }

    private fun convertLength(v: Double, from: String, to: String): Double {
        // Normalize to meters
        val meters = when (from) {
            "Meters"     -> v
            "Feet"       -> v / 3.28084
            "Kilometers" -> v * 1000.0
            "Miles"      -> v * 1609.344
            else         -> v
        }
        // Convert from meters to target
        return when (to) {
            "Meters"     -> meters
            "Feet"       -> meters * 3.28084
            "Kilometers" -> meters / 1000.0
            "Miles"      -> meters / 1609.344
            else         -> v
        }
    }

    private fun convertWeight(v: Double, from: String, to: String): Double {
        // Normalize to kilograms
        val kg = when (from) {
            "Kilograms" -> v
            "Pounds"    -> v / 2.20462
            "Grams"     -> v / 1000.0
            else        -> v
        }
        // Convert from kilograms to target
        return when (to) {
            "Kilograms" -> kg
            "Pounds"    -> kg * 2.20462
            "Grams"     -> kg * 1000.0
            else        -> v
        }
    }

    private fun format(v: Double): String {
        // Round to 2 decimals; avoid showing "-0.00"
        val rounded = round(v * 100.0) / 100.0
        return if (abs(rounded) < 0.005) "0.00" else String.format("%.2f", rounded)
        // If you prefer locale-aware formatting, use NumberFormat instead.
    }
}
