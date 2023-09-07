package com.myportfolio.myweatherapp.domain.model

import com.myportfolio.myweatherapp.data.dto.ConditionDTO
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

fun ForecastInfo.getDateString(): String {
    if (date.length < 10) {
        return date
    } else {
        val day = date.subSequence(8, 10)
        val month = date.subSequence(5, 7)
        val year = date.subSequence(0, 4)
        return day.toString() + "/" + month.toString() + "/" + year.toString()
    }
}