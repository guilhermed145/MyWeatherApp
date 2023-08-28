package com.myportfolio.myweatherapp.ui


/**
 * App UI State
 */
data class AppUiState(
    val currentScreen: String = "main",
    val searchBarText: String = "",
    val isSearchBarActive: Boolean = false,
    val searchBarHistory: List<String> = listOf(),
    val searchResultList: List<String> = listOf(),
)