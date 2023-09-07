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

    val weatherInfo: WeatherInfo =
        WeatherInfo(
            cloud = 0,
            condition = Condition(
                icon = "",
                text = ""
            ),
            is_day = 0,
            precip_mm = 0.0,
            temp_c = 0.0,
            temp_f = 0.0,
            wind_dir = "",
            wind_kph = 0.0,
        ),
    val currentLocation: Location =
        Location(
            country = "",
            lat = 0.0,
            localtime = "",
            lon = 0.0,
            name = "",
            region = "",
            tz_id = ""
        ),
    val forecastDayList: List<ForecastInfo> = mutableListOf(
        ForecastInfo(
            condition = Condition(
                icon = "",
                text = ""
            ),
            date = "",
            daily_chance_of_rain = 0,
            daily_chance_of_snow = 0,
            maxtemp_c = 0.0,
            maxtemp_f = 0.0,
            maxwind_kph = 0.0,
            mintemp_c = 0.0,
            mintemp_f = 0.0
        )
    ),
    val locationSearchResultList: List<Location> = listOf(
        Location(
            country = "",
            lat = 0.0,
            localtime = "",
            lon = 0.0,
            name = "",
            region = "",
            tz_id = ""
        ),
    ),
    val searchBarText: String = "",
    val showSearchHistory: Boolean = false,
    val locationHistoryList: List<Location> = listOf(),
)