package com.myportfolio.myweatherapp.ui

import com.myportfolio.myweatherapp.domain.model.Condition
import com.myportfolio.myweatherapp.domain.model.Location
import com.myportfolio.myweatherapp.domain.model.WeatherInfo


/**
 * The UI State stores all the states used in the app.
 */
data class AppUiState(
    val currentScreen: String = "main",
    val isWeatherLoading: Boolean = false,
    val searchBarText: String = "",
    val showSearchHistory: Boolean = false,
    val locationHistoryList: List<Location> = listOf(),
    val hasWeatherBeenFound: Boolean = true,

    val weatherInfo: WeatherInfo =
        WeatherInfo(
            cloud = 0,
            condition = Condition(
                iconUrl = "",
                text = ""
            ),
            precipMm = 0.0,
            tempC = 0.0,
            windDir = "",
            windKph = 0.0,
            forecastList = listOf(),
            weatherLocation = Location(
                coordinates = "",
                country = "",
                name = "",
                region = "",
            ),
        ),

    val locationSearchResultList: List<Location> = listOf(
        Location(
            coordinates = "",
            country = "",
            name = "",
            region = "",
        ),
    ),
)