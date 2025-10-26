package com.example.unitconverter.viewmodel

import com.example.unitconverter.domain.ConverterEngine
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConverterViewModelTest {

    @MockK
    lateinit var engine: ConverterEngine

    private lateinit var viewModel: ConverterViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = ConverterViewModel(engine)
    }

    @Test
    fun convert_delegatesToEngine_andSetsResult() {
        // Arrange
        viewModel.category = "Temperature"
        viewModel.inputUnit = "Celsius"
        viewModel.outputUnit = "Fahrenheit"
        viewModel.inputValue = "100"

        every {
            engine.convert("Temperature", "Celsius", "Fahrenheit", "100")
        } returns "212.00"

        // Act
        viewModel.convert()

        // Assert
        assertEquals("212.00", viewModel.result)
    }

    @Test
    fun swapUnits_swapsInputAndOutput() {
        // Arrange
        viewModel.inputUnit = "Meters"
        viewModel.outputUnit = "Feet"

        // Act
        viewModel.swapUnits()

        // Assert
        assertEquals("Feet", viewModel.inputUnit)
        assertEquals("Meters", viewModel.outputUnit)
    }

    @Test
    fun convert_withEmptyInput_setsEmptyResult() {
        // Arrange
        viewModel.category = "Length"
        viewModel.inputUnit = "Meters"
        viewModel.outputUnit = "Feet"
        viewModel.inputValue = ""

        every {
            engine.convert("Length", "Meters", "Feet", "")
        } returns ""

        // Act
        viewModel.convert()

        // Assert
        assertEquals("", viewModel.result)
    }

    @Test
    fun convert_differentCategory_weightExample() {
        // Arrange
        viewModel.category = "Weight"
        viewModel.inputUnit = "Kilograms"
        viewModel.outputUnit = "Pounds"
        viewModel.inputValue = "2.5"

        every {
            engine.convert("Weight", "Kilograms", "Pounds", "2.5")
        } returns "5.51"

        // Act
        viewModel.convert()

        // Assert
        assertEquals("5.51", viewModel.result)
    }
}