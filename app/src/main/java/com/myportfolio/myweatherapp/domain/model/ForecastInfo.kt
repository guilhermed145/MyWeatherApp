package com.myportfolio.myweatherapp.domain.model

data class ForecastInfo (
    val condition: Condition,
    val date: String,
    val dailyChanceOfRain: Int,
    val dailyChanceOfSnow: Int,
    val maxtempC: Double,
    val maxtempF: Double,
    val maxwindKph: Double,
    val mintempC: Double,
    val mintempF: Double
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