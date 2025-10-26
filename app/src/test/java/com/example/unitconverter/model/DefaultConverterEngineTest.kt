package com.example.unitconverter.model

import com.example.unitconverter.domain.DefaultConverterEngine
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultConverterEngineTest {

    private val engine = DefaultConverterEngine()

    @Test
    fun celsiusToFahrenheit() {
        val result = engine.convert("Temperature", "Celsius", "Fahrenheit", "100")
        assertEquals("212.00", result)
    }

    @Test
    fun metersToFeet() {
        val result = engine.convert("Length", "Meters", "Feet", "1")
        assertEquals("3.28", result) // rounded to 2 decimals
    }

    @Test
    fun poundsToKilograms() {
        val result = engine.convert("Weight", "Pounds", "Kilograms", "2.20462")
        assertEquals("1.00", result)
    }
}
