package com.example.bconverter.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bconverter.ui.ConversionsScreen.valueOf
import com.example.bconverter.ui.icons.arrow_back
import com.example.bconverter.ui.screens.MenuScreen
import com.example.bconverter.ui.screens.MoneyExchangeScreen
import com.example.bconverter.ui.screens.TemperatureConverterScreen

enum class ConversionsScreen(val title: String) {
    ConversionsMenu(title = "Conversions menu"),
    MoneyExchange(title = "Money exchange"),
    TemperatureConverter(title = "Temperature converter"),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionsAppBar(
    currentScreen: ConversionsScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                Icon(
                    imageVector = arrow_back,
                    contentDescription = null,
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        navigateUp()
                    })
                )
            }
        }
    )
}

@Composable
fun MyConversionsApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = valueOf(
        backStackEntry?.destination?.route ?: ConversionsScreen.ConversionsMenu.name
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ConversionsAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = ConversionsScreen.ConversionsMenu.name) {
            composable(route = ConversionsScreen.MoneyExchange.name) {
                MoneyExchangeScreen(modifier = Modifier.padding(innerPadding))
            }
            composable(route = ConversionsScreen.TemperatureConverter.name) {
                TemperatureConverterScreen(modifier = Modifier.padding(innerPadding))
            }
            composable(route = ConversionsScreen.ConversionsMenu.name) {
                MenuScreen(
                    modifier = Modifier.padding(innerPadding),
                    onMoneyExchangeClick = {
                        navController.navigate(route = ConversionsScreen.MoneyExchange.name)
                    },
                    onTemperatureConverterClick = {
                        navController.navigate(route = ConversionsScreen.TemperatureConverter.name)
                    }
                )
            }
        }
    }
}