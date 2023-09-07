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

interface AppRepository {
    /** Retrieves weather info from underlying data source */
    suspend fun getWeatherInfo(cityName: String): MainDTO

    /** Retrieves the location search results from underlying data source */
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

fun CurrentDTO.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        cloud = cloud,
        condition = condition.toCondition(),
        is_day = is_day,
        precip_mm = precip_mm,
        temp_c = temp_c,
        temp_f = temp_f,
        wind_dir = wind_dir,
        wind_kph = wind_kph,
    )
}

fun LocationDTO.toLocation(): Location {
    return Location(
        country = country,
        lat = lat,
        localtime = localtime,
        lon = lon,
        name = name,
        region = region,
        tz_id = tz_id
    )
}

fun ForecastdayDTO.toForecastInfo(): ForecastInfo {
    return ForecastInfo(
        condition = day.condition.toCondition(),
        date = date,
        daily_chance_of_rain = day.daily_chance_of_rain,
        daily_chance_of_snow = day.daily_chance_of_snow,
        maxtemp_c = day.maxtemp_c,
        maxtemp_f = day.maxtemp_f,
        maxwind_kph = day.maxwind_kph,
        mintemp_c = day.mintemp_c,
        mintemp_f = day.mintemp_f,
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
        localtime = "",
        lon = lon,
        name = name,
        region = region,
        tz_id = ""
    )
}