package com.example.unitconverter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unitconverter.domain.ConverterEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val engine: ConverterEngine
) : ViewModel() {

    var category: String by mutableStateOf("Temperature")
    var inputUnit: String by mutableStateOf("Celsius")
    var outputUnit: String by mutableStateOf("Fahrenheit")
    var inputValue: String by mutableStateOf("")
    var result: String by mutableStateOf("")

    fun convert() {
        result = engine.convert(category, inputUnit, outputUnit, inputValue)
    }

    fun swapUnits() {
        val tmp = inputUnit
        inputUnit = outputUnit
        outputUnit = tmp
    }
}
