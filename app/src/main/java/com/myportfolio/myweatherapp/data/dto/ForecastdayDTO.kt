package com.myportfolio.myweatherapp.data.dto

data class ForecastdayDTO(
    val astro: AstroDTO,
    val date: String,
    val date_epoch: Int,
    val day: DayDTO,
    val hour: List<HourDTO>
)