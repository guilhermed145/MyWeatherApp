package com.myportfolio.myweatherapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.myportfolio.myweatherapp.MainApplication
import com.myportfolio.myweatherapp.data.AppRepository
import com.myportfolio.myweatherapp.data.toForecastInfo
import com.myportfolio.myweatherapp.data.toLocation
import com.myportfolio.myweatherapp.data.toLocaton
import com.myportfolio.myweatherapp.data.toWeatherInfo
import com.myportfolio.myweatherapp.domain.model.ForecastInfo
import com.myportfolio.myweatherapp.domain.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * The ViewModel Class
 */
class AppViewModel(private val appRepository: AppRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getWeatherInfo("-23.53, -46.62")
    }

    // Tells the app to change the screen
    fun changeScreen(nextScreen : String){
        _uiState.update {
            it.copy(currentScreen = nextScreen)
        }
    }

    fun updateSearchBarText(text: String = "") {
        if (text == "") {
            if (!uiState.value.showSearchHistory) {
                _uiState.update {
                    it.copy(showSearchHistory = true)
                }
            }
        }
        _uiState.update {
            it.copy(searchBarText = text)
        }
    }

    fun addToLocationHistoryList(location: Location) {
        val newHistory: MutableList<Location> = mutableListOf()
        newHistory.addAll(uiState.value.locationHistoryList)
        if (newHistory.contains(location)) {
            newHistory.remove(location)
        }
        newHistory.add(location)
        _uiState.update {
            it.copy(locationHistoryList = newHistory)
        }
    }

    fun getWeatherInfo(cityLocation: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isWeatherLoading = true
                )
            }
            try {
                val apiData = appRepository.getWeatherInfo(cityLocation)
                val forecastdayList = mutableListOf<ForecastInfo>()
                for (day in apiData.forecast.forecastday) {
                    forecastdayList.add(day.toForecastInfo())
                }
                _uiState.update {
                    it.copy(
                        weatherInfo = apiData.current.toWeatherInfo(),
                        currentLocation = apiData.location.toLocation(),
                        forecastDayList = forecastdayList,
                    )
                }
            } catch (e: IOException) {
                Log.e("IOEXCEPTION", "getWeatherInfo()", e)
            } catch (e: HttpException) {
                Log.e("HTTPEXCEPTION", "getWeatherInfo()", e)
            }
            _uiState.update {
                it.copy(
                    isWeatherLoading = false
                )
            }
        }
    }

    fun getLocationSearchResults() {
        viewModelScope.launch {
            try {
                val apiData = appRepository.getLocationSearchResults(uiState.value.searchBarText)
                val foundLocationList = mutableListOf<Location>()
                for (locationItem in apiData) {
                    foundLocationList.add(locationItem.toLocaton())
                }
                _uiState.update {
                    it.copy(
                        locationSearchResultList = foundLocationList,
                        showSearchHistory = false
                    )
                }
            } catch (e: IOException) {
                Log.e("API", "getLocationSearchResults()", e)
            } catch (e: HttpException) {
                Log.e("HTTPEXCEPTION", "getLocationSearchResults()", e)
            }
        }
    }

    /**
     * Factory for the viewModel that takes the repository as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                    as MainApplication)
                val appRepository = application.container.appRepository
                AppViewModel(appRepository = appRepository)
            }
        }
    }
}