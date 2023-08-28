package com.myportfolio.myweatherapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * The ViewModel Class
 */
class AppViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow()

    // Tells the app to change the screen
    fun changeScreen(nextScreen : String){
        _uiState.update {
            it.copy(currentScreen = nextScreen)
        }
    }

}