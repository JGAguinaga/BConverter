package com.example.bconverter.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bconverter.ui.screens.MenuScreen
import com.example.bconverter.ui.screens.MoneyExchangeScreen
import com.example.bconverter.ui.screens.TemperatureConverterScreen
import kotlinx.serialization.Serializable

@Serializable
object MoneyExchange
@Serializable
object TemperatureConverter


@Serializable
object Menu
@Composable
fun MyConversionsApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(navController, startDestination = Menu) {
            composable<MoneyExchange> {
                MoneyExchangeScreen(modifier = Modifier.padding(innerPadding))
            }
            composable<TemperatureConverter> {
                TemperatureConverterScreen(modifier = Modifier.padding(innerPadding))
            }
            composable<Menu> {
                MenuScreen(
                    modifier = Modifier.padding(innerPadding),
                    onMoneyExchangeClick = {
                        navController.navigate(route = MoneyExchange)
                    },
                    onTemperatureConverterClick = {
                        navController.navigate(route = TemperatureConverter)
                    }
                )
            }
        }
    }
}