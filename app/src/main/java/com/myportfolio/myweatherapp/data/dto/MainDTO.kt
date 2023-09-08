package com.myportfolio.myweatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MainDTO(
    val alerts: AlertsDTO,
    val current: CurrentDTO,
    val forecast: ForecastDTO,
    val location: LocationDTO
)