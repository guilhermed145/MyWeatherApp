package com.myportfolio.myweatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationSearchItemDTO(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)