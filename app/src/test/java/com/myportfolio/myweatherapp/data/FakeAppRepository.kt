package com.myportfolio.myweatherapp.data

import com.myportfolio.myweatherapp.domain.model.Condition
import com.myportfolio.myweatherapp.domain.model.Location
import com.myportfolio.myweatherapp.domain.model.WeatherInfo

class FakeAppRepository: AppRepository {
    override suspend fun getWeatherInfo(cityLocation: String): WeatherInfo {
        return WeatherInfo(
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
                country = cityLocation,
                lat = 0.0,
                localTime = "",
                lon = 0.0,
                name = "",
                region = "",
                tzId = "",
            ),
        )
    }

    override suspend fun getLocationSearchResults(cityName: String): List<Location> {
        return listOf(
            Location(
                country = "",
                lat = 0.0,
                localTime = "",
                lon = 0.0,
                name = cityName,
                region = "",
                tzId = "1",
            ),
            Location(
                country = "",
                lat = 1.0,
                localTime = "",
                lon = 1.0,
                name = cityName,
                region = "",
                tzId = "2",
            ),
            Location(
                country = "",
                lat = 2.0,
                localTime = "",
                lon = 2.0,
                name = cityName,
                region = "",
                tzId = "3",
            )
        )
    }

}