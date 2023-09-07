package com.myportfolio.myweatherapp.domain.model



/**
 * Data class representing the weather information.
 */
data class WeatherInfo (
    val cloud: Int,
    val condition: Condition,
    val is_day: Int,
    val precip_mm: Double,
    val temp_c: Double,
    val temp_f: Double,
    val wind_dir: String,
    val wind_kph: Double,
    /**@SerialName("img_src") val imgSrc: String*/
)