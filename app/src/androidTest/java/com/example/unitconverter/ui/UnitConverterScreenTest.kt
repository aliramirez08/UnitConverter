package com.example.unitconverter

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class UnitConverterScreenTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val compose = createAndroidComposeRule<MainActivity>()

    @Test
    fun length_flow_shows_result() {
        hiltRule.inject()

        compose.onNodeWithText("Category").performClick()
        compose.onNodeWithText("Length").performClick()

        compose.onNodeWithText("From").performClick()
        compose.onNodeWithText("Meters").performClick()

        compose.onNodeWithText("To").performClick()
        compose.onNodeWithText("Feet").performClick()

        compose.onNodeWithText("Enter Value").performTextInput("1")
        compose.onNodeWithText("Convert").performClick()

        compose.onNodeWithText("3.28").assertIsDisplayed()
    }
}
