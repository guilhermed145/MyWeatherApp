package com.myportfolio.myweatherapp.ui

import com.myportfolio.myweatherapp.domain.model.Condition
import com.myportfolio.myweatherapp.domain.model.ForecastInfo
import com.myportfolio.myweatherapp.domain.model.Location
import com.myportfolio.myweatherapp.domain.model.WeatherInfo


/**
 * App UI State
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
        ),
    val currentLocation: Location =
        Location(
            country = "",
            lat = 0.0,
            localTime = "",
            lon = 0.0,
            name = "",
            region = "",
            tzId = ""
        ),
    val forecastDayList: List<ForecastInfo> = mutableListOf(
        ForecastInfo(
            condition = Condition(
                icon = "",
                text = ""
            ),
            date = "",
            dailyChanceOfRain = 0,
            dailyChanceOfSnow = 0,
            maxtempC = 0.0,
            maxtempF = 0.0,
            maxwindKph = 0.0,
            mintempC = 0.0,
            mintempF = 0.0
        )
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