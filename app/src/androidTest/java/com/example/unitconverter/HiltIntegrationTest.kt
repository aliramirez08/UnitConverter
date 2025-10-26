package com.example.unitconverter

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HiltIntegrationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val compose = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun e2e_temperature_returns_999() {
        compose.onNodeWithText("Category").performClick()
        compose.onNodeWithText("Temperature").performClick()
        compose.onNodeWithText("Enter Value").performTextInput("100")
        compose.onNodeWithText("Convert").performClick()
        compose.onNodeWithText("999").assertIsDisplayed()
    }
}
