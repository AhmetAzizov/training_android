package com.example.training_android

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.accompanist.systemuicontroller.SystemUiController

object AppState {
    var username: String = ""
    var isPremium: MutableState<Boolean> = mutableStateOf(false)
    var systemUiController: SystemUiController? = null
}