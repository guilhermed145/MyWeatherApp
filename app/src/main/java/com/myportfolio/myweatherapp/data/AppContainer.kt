package com.myportfolio.myweatherapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.myportfolio.myweatherapp.network.WeatherApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppContainer {
    val appRepository: AppRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://api.weatherapi.com/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
//      .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    /**
     * DI implementation for Amphibians repository
     */
    override val appRepository: AppRepository by lazy {
        DefaultAppRepository(retrofitService)
    }
}