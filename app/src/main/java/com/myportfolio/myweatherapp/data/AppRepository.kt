package com.myportfolio.myweatherapp.data

import com.myportfolio.myweatherapp.data.dto.ConditionDTO
import com.myportfolio.myweatherapp.data.dto.ForecastdayDTO
import com.myportfolio.myweatherapp.data.dto.LocationDTO
import com.myportfolio.myweatherapp.data.dto.LocationSearchDTO
import com.myportfolio.myweatherapp.data.dto.LocationSearchItemDTO
import com.myportfolio.myweatherapp.data.dto.MainDTO
import com.myportfolio.myweatherapp.domain.model.Condition
import com.myportfolio.myweatherapp.domain.model.ForecastInfo
import com.myportfolio.myweatherapp.domain.model.Location
import com.myportfolio.myweatherapp.domain.model.WeatherInfo
import com.myportfolio.myweatherapp.network.WeatherApiService

/**
 * The main Repository for the app.
 */
interface AppRepository {
    /** Retrieves weather info from underlying data source. */
    suspend fun getWeatherInfo(cityLocation: String): WeatherInfo

    /** Retrieves the location search results from underlying data source. */
    suspend fun getLocationSearchResults(cityName: String): List<Location>
}

class DefaultAppRepository(
    private val weatherApiService: WeatherApiService
) : AppRepository {
    override suspend fun getWeatherInfo(cityLocation: String): WeatherInfo {
//        val data = weatherApiService.getWeatherInfo(cityName = cityName)
        return weatherApiService.getWeatherInfo(cityLocation = cityLocation).toWeatherInfo()
    }

    override suspend fun getLocationSearchResults(cityName: String): List<Location> {
        return weatherApiService.getSearchResults(cityName = cityName).toLocationList()
    }
}

/**
 * DTO to Model mapping functions.
 */
fun MainDTO.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        cloud = current.cloud,
        condition = current.condition.toCondition(),
        precipMm = current.precip_mm,
        tempC = current.temp_c,
        windDir = current.wind_dir,
        windKph = current.wind_kph,
        forecastList = forecast.forecastday.map {
            it.toForecastInfo()
        },
        weatherLocation = location.toLocation()
    )
}

fun LocationDTO.toLocation(): Location {
    return Location(
        coordinates = "$lat, $lon",
        country = country,
        name = name,
        region = region,
    )
}

fun ForecastdayDTO.toForecastInfo(): ForecastInfo {
    return ForecastInfo(
        condition = day.condition.toCondition(),
        date = getForecastDateString(date),
        dailyChanceOfRain = day.daily_chance_of_rain,
        maxtempC = day.maxtemp_c,
        maxwindKph = day.maxwind_kph,
        mintempC = day.mintemp_c,
        totalPrecipMm = day.totalprecip_mm
    )
}

fun ConditionDTO.toCondition(): Condition {
    return Condition(
        iconUrl = "https:$icon",
        text = text
    )
}

fun LocationSearchDTO.toLocationList(): List<Location> {
    return this.map {
        it.toLocation()
    }
}

fun LocationSearchItemDTO.toLocation(): Location {
    return Location(
        coordinates = "$lat, $lon",
        country = country,
        name = name,
        region = region,
    )
}

/**
 * Returns the formatted date string for the forecast.
 */
fun getForecastDateString(date: String): String {
    return if (date.length < 10) {
        date
    } else {
        val day = date.subSequence(8, 10)
        val month = date.subSequence(5, 7)
        val year = date.subSequence(0, 4)
        "$day/$month/$year"
    }
}