package com.myportfolio.myweatherapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ConditionDTO(
    val code: Int,
    val icon: String,
    val text: String
)