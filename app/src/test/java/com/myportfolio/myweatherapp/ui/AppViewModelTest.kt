package com.myportfolio.myweatherapp.ui

import androidx.lifecycle.ViewModel
import com.myportfolio.myweatherapp.data.FakeAppRepository
import com.myportfolio.myweatherapp.domain.model.Location
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import retrofit2.http.GET

class AppViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun viewModel_ScreenChangeRequest_ScreenHasBeenChanged() {
        val mainScreenName = "main"
        val changeLocationScreenName = "change_location"

        return runTest {
            val viewModel = AppViewModel(appRepository = FakeAppRepository())

            viewModel.changeScreen(changeLocationScreenName)
            assertEquals(viewModel.uiState.value.currentScreen, "change_location")

            viewModel.changeScreen(mainScreenName)
            assertEquals(viewModel.uiState.value.currentScreen, "main")
        }
    }

    @Test
    fun viewModel_AddTextToSearchBar_TheRightTextHasBeenStored() = runTest {
        val viewModel = AppViewModel(appRepository = FakeAppRepository())

        viewModel.updateSearchBarText("test")
        assertEquals(viewModel.uiState.value.searchBarText, "test")

        viewModel.updateSearchBarText("")
        assertTrue(viewModel.uiState.value.showSearchHistory)
    }

    @Test
    fun viewModel_AddALocationToHistoryList_LocationSuccessfullyAddedToList() {
        val fakeLocation: Location =
            Location(
                country = "",
                lat = 0.0,
                localTime = "",
                lon = 0.0,
                name = "",
                region = "",
                tzId = ""
            )
        val fakeLocationCopy = fakeLocation.copy()

        return runTest {
            val viewModel = AppViewModel(appRepository = FakeAppRepository())

            viewModel.addToLocationHistoryList(fakeLocation)
            assertEquals(viewModel.uiState.value.locationHistoryList, listOf<Location>(fakeLocation))

            // Assert that the same location won't appear twice.
            viewModel.addToLocationHistoryList(fakeLocationCopy)
            assertEquals(viewModel.uiState.value.locationHistoryList, listOf<Location>(fakeLocation))
        }
    }

    @Test
    fun viewModel_RetrieveTheWeatherInformation_InformationSuccessfullyRetrieved() = runTest {
        val viewModel = AppViewModel(appRepository = FakeAppRepository())

        viewModel.getWeatherInfo("test")
        assertEquals(viewModel.uiState.value.weatherInfo.weatherLocation.country, "test")
        assertFalse(viewModel.uiState.value.isWeatherLoading)
    }

    @Test
    fun viewModel_RetrieveLocationSearchResults_ResultsSuccessfullyRetrieved() = runTest {
        val viewModel = AppViewModel(appRepository = FakeAppRepository())

        viewModel.updateSearchBarText("test")
        viewModel.getLocationSearchResults()
        assertEquals(viewModel.uiState.value.locationSearchResultList, FakeAppRepository().getLocationSearchResults("test"))
    }
}