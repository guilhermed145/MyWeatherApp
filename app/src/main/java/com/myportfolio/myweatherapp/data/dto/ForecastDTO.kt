package com.myportfolio.myweatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDTO(
    val forecastday: List<ForecastdayDTO>
)