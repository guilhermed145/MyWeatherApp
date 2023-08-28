package com.myportfolio.myweatherapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class representing the weather information.
 */
@Serializable
data class WeatherInfo (
    val name: String,
    val type: String,
    val description: String,
    @SerialName("img_src") val imgSrc: String
)