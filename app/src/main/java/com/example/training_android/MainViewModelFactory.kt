package com.example.training_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.training_android.Room.FavoriteDao

class MainViewModelFactory(
    private val dataStore: FavoritesDataStore,
    private val dao: FavoriteDao
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dataStore, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}