package com.example.unitconverter.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.unitconverter.model.UnitCategory
import com.example.unitconverter.viewmodel.ConverterViewModel

@Composable
fun UnitConverterScreen(
    viewModel: ConverterViewModel = hiltViewModel()
) {
    val categories = remember { UnitCategory.all() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("title")
        )

        DropdownSelector(
            label = "Category",
            options = categories.map { it.name },
            selected = viewModel.category,
            onSelected = {
                viewModel.category = it
                val units = UnitCategory.getByName(it)?.units ?: emptyList()
                if (units.isNotEmpty()) {
                    viewModel.inputUnit = units.first()
                    viewModel.outputUnit = units.last()
                }
            },
            modifier = Modifier.testTag("dropdownCategory")
        )

        val units = UnitCategory.getByName(viewModel.category)?.units ?: emptyList()

        DropdownSelector(
            label = "From",
            options = units,
            selected = viewModel.inputUnit,
            onSelected = { viewModel.inputUnit = it },
            modifier = Modifier.testTag("dropdownFrom")
        )

        DropdownSelector(
            label = "To",
            options = units,
            selected = viewModel.outputUnit,
            onSelected = { viewModel.outputUnit = it },
            modifier = Modifier.testTag("dropdownTo")
        )

        OutlinedTextField(
            value = viewModel.inputValue,
            onValueChange = { viewModel.inputValue = it },
            label = { Text("Enter Value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("input")
        )

        Button(
            onClick = { viewModel.convert() },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("convert")
        ) {
            Text("Convert")
        }

        Text(
            text = "Result: ${viewModel.result}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(top = 8.dp)
                .testTag("result")
        )
    }
}

@Composable
fun DropdownSelector(
    label: String,
    options: List<String>,
    selected: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = label, style = MaterialTheme.typography.labelLarge)
        Box {
            OutlinedTextField(
                value = selected,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .testTag("${label.lowercase()}Field"),
                readOnly = true,
                label = { Text(label) }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            onSelected(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
