package com.example.bconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bconverter.ui.MyConversionsApp
import com.example.bconverter.ui.theme.BConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BConverterTheme {
                MyConversionsApp()
            }
        }
    }
}