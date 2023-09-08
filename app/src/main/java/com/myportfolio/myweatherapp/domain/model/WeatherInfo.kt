package com.myportfolio.myweatherapp.domain.model



/**
 * Data class representing the weather information that appears in the main screen.
 */
data class WeatherInfo (
    val cloud: Int,
    val condition: Condition,
    val isDay: Int,
    val precipMm: Double,
    val tempC: Double,
    val tempF: Double,
    val windDir: String,
    val windKph: Double,
    /**@SerialName("img_src") val imgSrc: String*/
)

