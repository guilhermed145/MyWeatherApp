package com.myportfolio.myweatherapp.network

import com.myportfolio.myweatherapp.data.dto.LocationSearchDTO
import com.myportfolio.myweatherapp.data.dto.MainDTO

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Main API Service for the app. Retrieves all the data necessary from the weather API.
 */
interface WeatherApiService {
    @GET("forecast.json?")
    suspend fun getWeatherInfo(
        @Query("key") key : String = "c5f5ea18a2414fc8ac7125919232108",
        @Query("q") cityLocation: String,
        @Query("days") numberOfDays: Int = 3,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "yes",
    ): MainDTO

    @GET("search.json?")
    suspend fun getSearchResults(
        @Query("key") key : String = "c5f5ea18a2414fc8ac7125919232108",
        @Query("q") cityName: String,
    ): LocationSearchDTO
}
