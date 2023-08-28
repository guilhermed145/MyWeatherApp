package com.myportfolio.myweatherapp.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.material.textfield.TextInputEditText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeLocationScreen (
    modifier : Modifier = Modifier,
) {
    Column (
        modifier = modifier
    ) {
        TextField(value = "", onValueChange = {})
        Card () {
            
        }
    }
}