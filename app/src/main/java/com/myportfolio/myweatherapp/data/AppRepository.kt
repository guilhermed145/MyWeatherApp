package com.myportfolio.myweatherapp.data

import com.myportfolio.myweatherapp.data.dto.ConditionDTO
import com.myportfolio.myweatherapp.data.dto.CurrentDTO
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
    suspend fun getWeatherInfo(cityName: String): MainDTO

    /** Retrieves the location search results from underlying data source. */
    suspend fun getLocationSearchResults(cityName: String): LocationSearchDTO
}

class DefaultAppRepository(
    private val weatherApiService: WeatherApiService
) : AppRepository {
    override suspend fun getWeatherInfo(cityName: String): MainDTO {
//        val data = weatherApiService.getWeatherInfo(cityName = cityName)
        return weatherApiService.getWeatherInfo(cityLocation = cityName)
    }

    override suspend fun getLocationSearchResults(cityName: String): LocationSearchDTO {
        return weatherApiService.getSearchResults(cityName = cityName)
    }
}

/**
 * DTO to Model mapping functions.
 */
fun CurrentDTO.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        cloud = cloud,
        condition = condition.toCondition(),
        isDay = is_day,
        precipMm = precip_mm,
        tempC = temp_c,
        tempF = temp_f,
        windDir = wind_dir,
        windKph = wind_kph,
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