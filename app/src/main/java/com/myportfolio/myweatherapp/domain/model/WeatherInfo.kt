package com.myportfolio.myweatherapp.domain.model



/**
 * Data class representing the weather information that appears in the main screen.
 */
data class WeatherInfo(
    val cloud: Int,
    val condition: Condition,
    val precipMm: Double,
    val tempC: Double,
    val windDir: String,
    val windKph: Double,
    val forecastList: List<ForecastInfo>,
    val weatherLocation: Location,
    /**@SerialName("img_src") val imgSrc: String*/
)

