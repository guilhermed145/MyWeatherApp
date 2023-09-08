package com.myportfolio.myweatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AlertDTO(
    val areas: String,
    val category: String,
    val certainty: String,
    val desc: String,
    val effective: String,
    val event: String,
    val expires: String,
    val headline: String,
    val instruction: String,
    val msgtype: String,
    val note: String,
    val severity: String,
    val urgency: String
)