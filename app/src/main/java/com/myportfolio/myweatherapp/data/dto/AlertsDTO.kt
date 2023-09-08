package com.myportfolio.myweatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AlertsDTO(
    val alert: List<AlertDTO>
)