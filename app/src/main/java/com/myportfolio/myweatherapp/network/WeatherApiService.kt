package com.myportfolio.myweatherapp.network

import com.myportfolio.myweatherapp.domain.model.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiService {
    @GET("http://api.weatherapi.com/v1/current.json?key=c5f5ea18a2414fc8ac7125919232108&q={cityName}&aqi=no")
    suspend fun getWeatherInfo(@Path("cityName") cityName : String) : WeatherInfo
}