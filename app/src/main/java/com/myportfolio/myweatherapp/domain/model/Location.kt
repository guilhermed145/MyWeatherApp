package com.myportfolio.myweatherapp.domain.model



/**
 * Data class representing the location information.
 */
data class Location (
    val country: String,
    val lat: Double,
    val localTime: String,
    val lon: Double,
    val name: String,
    val region: String,
    val tzId: String
)

fun Location.getCoordinatesString(): String {
    return lat.toString() + ", " + lon.toString()
}