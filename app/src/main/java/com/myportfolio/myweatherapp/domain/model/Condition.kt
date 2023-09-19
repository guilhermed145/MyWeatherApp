package com.myportfolio.myweatherapp.domain.model

/**
 * Data class representing the weather condition, that has a weather icon and a weather description.
 */
data class Condition (
    val iconUrl: String,
    val text: String
)
