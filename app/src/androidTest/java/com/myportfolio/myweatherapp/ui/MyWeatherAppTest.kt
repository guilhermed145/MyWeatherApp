package com.myportfolio.myweatherapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.myportfolio.myweatherapp.ui.theme.MyWeatherAppTheme
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyWeatherAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyWeatherAppTheme {
                MyWeatherApp()
            }
        }
    }

//    @Test
//    fun myWeatherApp() {
//        composeTestRule.onNodeWithText("Continue").performClick()
//        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
//    }
}