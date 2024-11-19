package com.example.training_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.training_android.ui.theme.Training_androidTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            val systemUiController = rememberSystemUiController()

            AppState.systemUiController = systemUiController

            Training_androidTheme {

                systemUiController.setStatusBarColor(MaterialTheme.colorScheme.background)
                systemUiController.setNavigationBarColor(MaterialTheme.colorScheme.background)
                Navigation()
            }
        }
    }
}

