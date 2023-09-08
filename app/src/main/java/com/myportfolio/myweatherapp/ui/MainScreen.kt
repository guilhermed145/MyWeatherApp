package com.myportfolio.myweatherapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.myportfolio.myweatherapp.domain.model.ForecastInfo
import com.myportfolio.myweatherapp.domain.model.Location
import com.myportfolio.myweatherapp.domain.model.WeatherInfo
import com.myportfolio.myweatherapp.domain.model.getDateString
import com.myportfolio.myweatherapp.domain.model.getIconUrl

/**
 * The app's main screen. It shows all the weather information about the chosen location.
 * */
@Composable
fun MainScreen(
    weatherInfo: WeatherInfo,
    location: Location,
    forecastDayList: List<ForecastInfo>,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onRefresh: () -> Unit = {},
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isLoading),
        onRefresh = onRefresh
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            item {
                LocationInfo(
                    location = location
                )
            }
            item {
                WeatherInfo(
                    weatherInfo = weatherInfo
                )
            }
            item {
                FutureWeatherInfo(
                    forecastDayList = forecastDayList
                )
            }
        }
    }
}

/**
 * Composable that shows the current location information.
 */
@Composable
fun LocationInfo(
    location: Location,
    modifier : Modifier = Modifier
) {
    Column (
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
        ) {
            Text(
                text = location.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
        ) {
            Text(
                text = location.region + ", "
            )
            Text(
                text = location.country
            )
        }
    }
}

/**
 * Composable that shows the current weather information.
 */
@Composable
fun WeatherInfo(
    weatherInfo: WeatherInfo,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(weatherInfo.condition.getIconUrl())
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.width(64.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = weatherInfo.condition.text,
                modifier = Modifier.align(CenterVertically)
            )
        }
        Row(
            modifier.fillMaxWidth()
        ) {
            Text(
                text = weatherInfo.tempC.toString() + "ºC",
                modifier = Modifier.weight(1f),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Wind direction: " + weatherInfo.windDir,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }
        Row {
            Text(
                text = "Wind speed: " + weatherInfo.windKph.toString() + "Km/h",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(
            text = "Precipitation: " + weatherInfo.precipMm.toString() + "mm",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Composable that shows the weather information for today and the next two days.
 */
@Composable
fun FutureWeatherInfo(
    forecastDayList: List<ForecastInfo>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        forecastDayList.forEach {
            FutureWeatherCard(forecastInfo = it)
        }
    }
}

/**
 * Contains the weather information about a specific day.
 */
@Composable
fun FutureWeatherCard(
    forecastInfo: ForecastInfo,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(forecastInfo.condition.getIconUrl())
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.width(64.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = forecastInfo.condition.text,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = forecastInfo.getDateString(),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }
            Text(
                text = "Min " + forecastInfo.mintempC.toString() + "ºC - Max "
                        + forecastInfo.maxtempC.toString() + "ºC"
            )
        }
    }
}