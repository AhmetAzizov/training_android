package com.example.training_android

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.training_android.Room.FavoritesDatabase
import com.example.training_android.View.ChatScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val favoriteDao = FavoritesDatabase.getDatabase(context).getDao()

    val viewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(FavoritesDataStore(context), favoriteDao)
    )

    NavHost(navController = navController, startDestination = ScreenNames.HOME_SCREEN) {
        composable(ScreenNames.HOME_SCREEN) {
            HomeScreen(viewModel, navController)
        }

        composable(ScreenNames.CHAT_SCREEN) {
            ChatScreen(viewModel, navController)
        }
    }
}