package com.example.bconverter.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bconverter.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MoneyExchangeUiState(
    val cantidad: String = "",
    val tipoCambio: String = "",
    val tipoConversion: String = "PD",
    val resultado: String = "",
    val flag1: Int = R.drawable.mex,
    val flag2: Int = R.drawable.usa
)

class MoneyExchangeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MoneyExchangeUiState())
    val uiState: StateFlow<MoneyExchangeUiState> = _uiState.asStateFlow()

    fun updateCantidad(cantidad: String) {
        _uiState.value = _uiState.value.copy(cantidad = cantidad)
    }

    fun updateTipoCambio(tipoCambio: String) {
        _uiState.value = _uiState.value.copy(tipoCambio = tipoCambio)
    }

    fun updateTipoConversion(tipoConversion: String) {
        _uiState.value = _uiState.value.copy(tipoConversion = tipoConversion)
    }

    fun calcular(){
        val resultado: Double
        val flag1: Int
        val flag2: Int

        if(_uiState.value.tipoConversion == "PD"){
            resultado = _uiState.value.cantidad.toDouble() / _uiState.value.tipoCambio.toDouble()
            flag1 = R.drawable.mex
            flag2 = R.drawable.usa
        }
        else{
            resultado = _uiState.value.cantidad.toDouble() * _uiState.value.tipoCambio.toDouble()
            flag1 = R.drawable.usa
            flag2 = R.drawable.mex
        }

        _uiState.value = _uiState.value.copy(resultado = resultado.toString(), flag1 = flag1, flag2 = flag2)
    }
}