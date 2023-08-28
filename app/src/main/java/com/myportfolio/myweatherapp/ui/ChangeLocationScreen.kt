package com.myportfolio.myweatherapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ChangeLocationScreen (
    modifier : Modifier = Modifier,
) {
    var text by remember{ mutableStateOf("") }
    Column (
        modifier = modifier
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Search location")
            },
            placeholder = {
                Text(text = "New York")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search icon")
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            shape = RoundedCornerShape(40.dp)
        )
        LazyColumn () {
            item{
                PastLocationsList()
            }
        }
    }
}

@Composable
fun PastLocationsList(
    numOfLocations : List<Int> = mutableListOf(),
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        numOfLocations.forEach {
            PastLocationCard()
        }
    }
}

@Composable
fun PastLocationCard() {
    Card {
        Text(text = "Location")
    }
}
