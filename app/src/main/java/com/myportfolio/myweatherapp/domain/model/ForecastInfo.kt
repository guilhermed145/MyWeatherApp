package com.myportfolio.myweatherapp.domain.model

/**
 * Data class representing the average weather information for today and the next 2 days.
 */
data class ForecastInfo(
    val condition: Condition,
    val date: String,
    val dailyChanceOfRain: Int,
    val maxtempC: Double,
    val maxwindKph: Double,
    val mintempC: Double,
    val totalPrecipMm: Double
)