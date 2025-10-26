package com.example.unitconverter.domain

/**
 * Converts a numeric [value] from [from] unit to [to] unit for a [category].
 * Returns a formatted string or empty string if input is invalid.
 */
interface ConverterEngine {
    fun convert(category: String, from: String, to: String, value: String): String
}