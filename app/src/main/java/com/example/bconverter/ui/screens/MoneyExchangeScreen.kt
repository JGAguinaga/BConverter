package com.example.bconverter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bconverter.ui.icons.arrow_forward
import kotlinx.coroutines.launch

@Composable
fun MoneyExchangeScreen(modifier: Modifier = Modifier, moneyExchangeViewModel: MoneyExchangeViewModel = viewModel()) {

    val uiState by moneyExchangeViewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Money exchange", fontWeight = FontWeight.Bold)

            TextField(
                value = uiState.cantidad,
                onValueChange = { moneyExchangeViewModel.updateCantidad(it) },
                label = { Text("Quantity") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            TextField(
                value = uiState.tipoCambio,
                onValueChange = { moneyExchangeViewModel.updateTipoCambio(it) },
                label = { Text("Exchange rate") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = uiState.tipoConversion == "DP",
                        onClick = { moneyExchangeViewModel.updateTipoConversion("DP") })
                    Text(text = "Dollars to pesos")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = uiState.tipoConversion == "PD",
                        onClick = { moneyExchangeViewModel.updateTipoConversion("PD") })
                    Text(text = "Pesos to dollars")
                }
            }

            Button(onClick = {
                val cantidadVal = uiState.cantidad.toDoubleOrNull()
                val tipoCambioVal = uiState.tipoCambio.toDoubleOrNull()

                if (cantidadVal == null || tipoCambioVal == null) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Datos incorrectos o faltantes")
                    }
                } else {
                    moneyExchangeViewModel.calcular()
                }
            }) {
                Text(text = "Convert")
            }

            if (uiState.resultado != "") {

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Resultado: ${uiState.resultado}",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )

                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = painterResource(id = uiState.flag1),
                        contentDescription = null
                    )

                    Image(imageVector = arrow_forward, modifier = Modifier.size(48.dp), contentDescription = null)

                    Image(
                        painter = painterResource(id = uiState.flag2),
                        contentDescription = null
                    )
                }
            }

        }
    }
}