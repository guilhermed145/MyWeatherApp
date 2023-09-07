package com.myportfolio.myweatherapp.domain.model

data class Condition (
    val icon: String,
    val text: String
)

fun Condition.getIconUrl(): String {
    return "https:" + icon
}