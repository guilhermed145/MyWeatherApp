package com.myportfolio.myweatherapp.domain.model

import com.myportfolio.myweatherapp.data.dto.ConditionDTO

data class ForecastInfo (
    val condition: Condition,
    val date: String,
    val daily_chance_of_rain: Int,
    val daily_chance_of_snow: Int,
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val maxwind_kph: Double,
    val mintemp_c: Double,
    val mintemp_f: Double
)