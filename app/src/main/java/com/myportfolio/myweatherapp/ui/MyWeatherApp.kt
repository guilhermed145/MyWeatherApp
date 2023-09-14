package com.myportfolio.myweatherapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myportfolio.myweatherapp.domain.model.getCoordinatesString

/**
 * The main composable for the app. It contains an instance for the Navigation Controller,
 * the ViewModel and the UIState, along with all the composables representing a screen.
 * Also contains a top bar with a search icon button that goes to the change location screen,
 * and the NavHost, responsible for navigation between the screens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyWeatherApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)
    val uiState by viewModel.uiState.collectAsState()

    val mainScreenToChangeLocationScreen: () -> Unit = {
        viewModel.changeScreen("change_location")
        navController.navigate("change_location")
    }
    val changeLocationScreenToMainScreen: () -> Unit = {
        viewModel.changeScreen("main")
        navController.popBackStack()
    }

    Scaffold (
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Weather App")
                },
                navigationIcon = {
                    if (uiState.currentScreen == "change_location") {
                        IconButton(
                            onClick = changeLocationScreenToMainScreen
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Turn back to main screen."
                            )
                        }
                    }
                },
                actions = {
                    if (uiState.currentScreen == "main") {
                        IconButton(
                            onClick = mainScreenToChangeLocationScreen,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = "main",
            ) {
                composable(route = "main") {
                    MainScreen(
                        weatherInfo = uiState.weatherInfo,
                        isLoading = uiState.isWeatherLoading,
                        onRefresh = {
                            viewModel.getWeatherInfo(uiState.weatherInfo.weatherLocation.getCoordinatesString())
                        }
                    )
                }
                composable(route = "change_location") {
                    BackHandler (
                        onBack = changeLocationScreenToMainScreen
                    )
                    ChangeLocationScreen(
                        showSearchHistory = uiState.showSearchHistory,
                        searchBarText = uiState.searchBarText,
                        searchBarHistory = uiState.locationHistoryList,
                        searchBarResults = uiState.locationSearchResultList,
                        onSearchBarTextChange = {viewModel.updateSearchBarText(it)},
                        onSearchButtonClicked = {viewModel.getLocationSearchResults()},
                        onLocationCardClick = {
                            viewModel.getWeatherInfo(it.getCoordinatesString())
                            viewModel.addToLocationHistoryList(it)
                            changeLocationScreenToMainScreen()
                        }
                    )
                }
            }
        }
    }
}
