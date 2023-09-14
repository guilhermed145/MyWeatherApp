package com.myportfolio.myweatherapp.ui

import com.myportfolio.myweatherapp.domain.model.Condition
import com.myportfolio.myweatherapp.domain.model.ForecastInfo
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

    val weatherInfo: WeatherInfo =
        WeatherInfo(
            cloud = 0,
            condition = Condition(
                icon = "",
                text = ""
            ),
            isDay = 0,
            precipMm = 0.0,
            tempC = 0.0,
            tempF = 0.0,
            windDir = "",
            windKph = 0.0,
            forecastList = listOf(),
            weatherLocation = Location(
                country = "",
                lat = 0.0,
                localTime = "",
                lon = 0.0,
                name = "",
                region = "",
                tzId = ""
            ),
        ),

    val locationSearchResultList: List<Location> = listOf(
        Location(
            country = "",
            lat = 0.0,
            localTime = "",
            lon = 0.0,
            name = "",
            region = "",
            tzId = ""
        ),
    ),
)