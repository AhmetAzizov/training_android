package com.example.training_android

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val dataStore: DataStore<Preferences>,
    private val dao: FavoriteDao
): ViewModel() {

    var number = mutableStateOf(0)
    var maxNumber = mutableStateOf(0)
    var favoriteList = mutableStateOf<List<Favorite>>(emptyList())

    private val NUMBER_KEY = intPreferencesKey("number")

    init {
        maxNumber.value = getNumber()

        viewModelScope.launch {
            dao.insertFavorite(Favorite(favoriteNumber = 5))
            dao.insertFavorite(Favorite(favoriteNumber = 2))
            dao.insertFavorite(Favorite(favoriteNumber = 12))
        }

        favoriteList.value = dao.getFavorites()
    }

    fun updateValue() {
        number.value += 1

        if (number.value > maxNumber.value) {
            saveNumber(maxNumber.value)
        }
    }

    fun resetNumber() {
        number.value = 0
    }

    fun saveNumber(number: Int) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[NUMBER_KEY] = number
            }

            maxNumber.value = number
        }
    }

    fun getNumber(): Int {
        var number: Int

        runBlocking {
            val preferences = dataStore.data.first()
            number = preferences[NUMBER_KEY] ?: 0
        }

        Log.d("getNumber", "getNumber: $number")

        return number
    }
}