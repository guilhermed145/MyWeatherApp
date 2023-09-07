package com.myportfolio.myweatherapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myportfolio.myweatherapp.domain.model.Location

@Composable
fun ChangeLocationScreen (
    modifier : Modifier = Modifier,
    showSearchHistory: Boolean = false,
    searchBarText: String = "",
    searchBarHistory: List<Location> = listOf(),
    searchBarResults: List<Location> = listOf(),
    onSearchBarTextChange: (String) -> Unit = {},
    onSearchButtonClicked: () -> Unit = {},
    onLocationCardClick: (Location) -> Unit = {}
) {
    Column (
        modifier = modifier
    ) {
        OutlinedTextField(
            value = searchBarText,
            onValueChange = {
                onSearchBarTextChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = {
                Text("Search location")
            },
            placeholder = {
                Text(
                    text = "New York",
                    color = Color.Gray
                )
            },
/*            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search icon")
            },*/
            trailingIcon = {
                IconButton(
                    onClick = onSearchButtonClicked
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search button"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchButtonClicked() }),
            shape = RoundedCornerShape(40.dp)
        )
        LazyColumn () {
            item{
                LocationsList(
                    locationList = if (showSearchHistory) {
                        searchBarHistory.asReversed()
                    } else {
                        searchBarResults
                    },
                    onLocationCardClick = onLocationCardClick
                )
            }
        }
    }
}

@Composable
fun LocationsList(
    modifier: Modifier = Modifier,
    locationList: List<Location> = listOf(),
    onLocationCardClick: (Location) -> Unit = {},
) {
    Column (
        modifier = modifier
    ) {
        for (location in locationList) {
            LocationCard(
                location = location,
                onClick = onLocationCardClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationCard(
    location: Location,
    modifier: Modifier = Modifier,
    onClick: (Location) -> Unit = {}
) {
    Card (
        onClick = { onClick(location) },
        modifier = modifier.fillMaxWidth()
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
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
}


