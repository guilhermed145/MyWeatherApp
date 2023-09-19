package com.myportfolio.myweatherapp.domain.model



/**
 * Data class representing the chosen location's information.
 */
data class Location(
    val coordinates: String,
    val country: String,
    val name: String,
    val region: String,
)