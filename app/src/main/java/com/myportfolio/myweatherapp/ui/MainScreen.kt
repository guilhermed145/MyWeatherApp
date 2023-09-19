package com.myportfolio.myweatherapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.myportfolio.myweatherapp.R
import com.myportfolio.myweatherapp.domain.model.ForecastInfo
import com.myportfolio.myweatherapp.domain.model.Location
import com.myportfolio.myweatherapp.domain.model.WeatherInfo

/**
 * The app's main screen. It shows all the weather information about the chosen location.
 * */
@Composable
fun MainScreen(
    weatherInfo: WeatherInfo,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    hasWeatherBeenFound: Boolean = true,
    onRefresh: () -> Unit = {},
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isLoading),
        onRefresh = onRefresh,
        modifier = modifier
    ) {
        if (hasWeatherBeenFound) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                item {
                    LocationInfo(
                        location = weatherInfo.weatherLocation
                    )
                }
                item {
                    WeatherInfo(
                        weatherInfo = weatherInfo
                    )
                }
                item {
                    FutureWeatherInfo(
                        forecastDayList = weatherInfo.forecastList
                    )
                }
            }
        } else {
            WeatherNotFoundLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            )
            /*
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    WeatherNotFoundLayout()
                }
            }
            */
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
            if (location.region != "") {
                Text(
                    text = location.region + ", "
                )
            }
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
    if (weatherInfo.weatherLocation.coordinates != "") {
        Column(
            modifier = modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = weatherInfo.tempC.toString() + "ºC",
                color = Color.DarkGray,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(weatherInfo.condition.iconUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Weather image",
                    modifier = Modifier.width(64.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = weatherInfo.condition.text,
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
            ) {
                WeatherPropertyText(
                    modifier = Modifier.weight(1f),
                    titleText = "Wind Speed",
                    valueText = weatherInfo.windKph.toString() + "Km/h",
                )
                WeatherPropertyText(
                    modifier = Modifier.weight(1f),
                    titleText = "Wind Direction",
                    valueText = weatherInfo.windDir,
                )
            }
            Row (
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)) {
                WeatherPropertyText(
                    modifier = Modifier.weight(1f),
                    titleText = "Precipitation",
                    valueText = weatherInfo.precipMm.toString() + "mm",
                )
                WeatherPropertyText(
                    modifier = Modifier.weight(1f),
                    titleText = "Cloud Coverage",
                    valueText = weatherInfo.cloud.toString() + "%",
                )
            }
        }
    }
}

@Composable
fun WeatherPropertyText(
    modifier: Modifier = Modifier,
    titleText: String = "",
    valueText: String = ""
) {
    Column (modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = valueText,
            color = Color.DarkGray,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = titleText,
            color = Color.Gray,
            textAlign = TextAlign.Center
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
        modifier = modifier.padding(vertical = 16.dp)
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
                        .data(forecastInfo.condition.iconUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.width(64.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = forecastInfo.condition.text,
                    modifier = Modifier.align(CenterVertically),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = forecastInfo.date,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.End
                )
            }
            Text(
                text = "Min " + forecastInfo.mintempC.toString() + "ºC - Max "
                        + forecastInfo.maxtempC.toString() + "ºC"
            )
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                ForecastPropertyText(
                    modifier = Modifier.weight(1f),
                    imagePainter = painterResource(R.drawable.chance_of_rain),
                    valueText = forecastInfo.dailyChanceOfRain.toString() + "%"
                )
                ForecastPropertyText(
                    modifier = Modifier.weight(1f),
                    imagePainter = painterResource(R.drawable.max_wind_speed),
                    valueText = forecastInfo.maxwindKph.toString() + "Km/h"
                )
            }
        }
    }
}

/**
 * Composable made of a row that contains an image and a text.
 */
@Composable
fun ForecastPropertyText (
    imagePainter: Painter,
    modifier: Modifier = Modifier,
    valueText: String = "",
) {
    Row (modifier = modifier.padding(top = 8.dp)) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .width(48.dp)
                .padding(end = 8.dp)
        )
        Text(
            text = valueText,
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterVertically),
        )
    }
}

/**
 * Composable that appears when the app can't find the weather.
 */
@Composable
fun WeatherNotFoundLayout(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Weather not found.",
            fontSize = 20.sp
        )
    }
}