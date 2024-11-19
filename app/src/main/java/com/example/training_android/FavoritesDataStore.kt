package com.example.training_android

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private const val TAG = "FavoritesDataStore"

class FavoritesDataStore(
    private val context: Context
){
    private val Context.dataStore by preferencesDataStore(name = "settings")
    private val NUMBER_KEY = intPreferencesKey("number")

    suspend fun saveNumber(number: Int) {
        context.dataStore.edit { preferences ->
            preferences[NUMBER_KEY] = number
        }
    }

    suspend fun getNumber(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[NUMBER_KEY] ?: 0
    }
}