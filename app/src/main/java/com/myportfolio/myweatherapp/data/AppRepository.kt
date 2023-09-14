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
        isDay = current.is_day,
        precipMm = current.precip_mm,
        tempC = current.temp_c,
        tempF = current.temp_f,
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
        country = country,
        lat = lat,
        localTime = localtime,
        lon = lon,
        name = name,
        region = region,
        tzId = tz_id
    )
}

fun ForecastdayDTO.toForecastInfo(): ForecastInfo {
    return ForecastInfo(
        condition = day.condition.toCondition(),
        date = date,
        dailyChanceOfRain = day.daily_chance_of_rain,
        dailyChanceOfSnow = day.daily_chance_of_snow,
        maxtempC = day.maxtemp_c,
        maxtempF = day.maxtemp_f,
        maxwindKph = day.maxwind_kph,
        mintempC = day.mintemp_c,
        mintempF = day.mintemp_f,
    )
}

fun ConditionDTO.toCondition(): Condition {
    return Condition(
        icon = icon,
        text = text
    )
}

fun LocationSearchDTO.toLocationList(): List<Location> {
    return this.map {
        it.toLocaton()
    }
}

fun LocationSearchItemDTO.toLocaton(): Location {
    return Location(
        country = country,
        lat = lat,
        localTime = "",
        lon = lon,
        name = name,
        region = region,
        tzId = ""
    )
}