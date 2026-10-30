package com.example.bconverter.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bconverter.ui.screens.MoneyExchangeScreen
import com.example.bconverter.ui.screens.TemperatureConverterScreen
import kotlinx.serialization.Serializable

@Serializable
object MoneyExchange
@Serializable
object TemperatureConverter

@Composable
fun MyConversionsApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(navController, startDestination = MoneyExchange) {
            composable<MoneyExchange> {
                MoneyExchangeScreen(modifier = Modifier.padding(innerPadding))
            }
            composable<TemperatureConverter> {
                TemperatureConverterScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}