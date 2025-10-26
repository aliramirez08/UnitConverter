package com.example.unitconverter.model

sealed class UnitCategory(val name: String, val units: List<String>) {
    object Temperature : UnitCategory("Temperature", listOf("Celsius", "Fahrenheit", "Kelvin"))
    object Length : UnitCategory("Length", listOf("Meters", "Feet", "Yards"))
    object Weight : UnitCategory("Weight", listOf("Kilograms", "Pounds", "Ounces"))

    companion object {
        fun getByName(name: String): UnitCategory? {
            return when (name) {
                Temperature.name -> Temperature
                Length.name -> Length
                Weight.name -> Weight
                else -> null
            }
        }

        fun all() = listOf(Temperature, Length, Weight)
    }
}
