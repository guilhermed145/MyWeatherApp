package com.myportfolio.myweatherapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myportfolio.myweatherapp.R

@Composable
fun MainScreen(
    modifier : Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item {
            LocationInfo()
        }
        item {
            WeatherInfo()
        }
        item {
            FutureWeatherInfo()
        }
    }
}

@Composable
fun LocationInfo(
    modifier : Modifier = Modifier
) {
    Column (
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
        ) {
            Text(
                text = "City Name",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
        ) {
            Text(
                text = "State Name" + ", "
            )
            Text(
                text = "Country Name"
            )
        }
    }
}

@Composable
fun WeatherInfo(
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.width(400.dp),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier.fillMaxWidth()
        ) {
            Text(
                text = "30ºC SUNNY",
                modifier = Modifier.weight(1f),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "23/08",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }
        Row {
            Text(
                text = "Min 21ºC - Max 32º",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(
            text = "Precipitation: 20mm",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun FutureWeatherInfo(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        FutureWeatherCard()
        FutureWeatherCard()
        FutureWeatherCard()
    }
}

@Composable
fun FutureWeatherCard(
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
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                )
                Text(
                    text = "Thursday",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = "24/08",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }
            Text(
                text = "Min 21ºC - Max 32ºC"
            )
        }
    }
}