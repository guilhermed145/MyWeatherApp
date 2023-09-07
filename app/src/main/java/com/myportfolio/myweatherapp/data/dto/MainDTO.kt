package com.myportfolio.myweatherapp.data.dto

data class MainDTO(
    val alerts: AlertsDTO,
    val current: CurrentDTO,
    val forecast: ForecastDTO,
    val location: LocationDTO
)