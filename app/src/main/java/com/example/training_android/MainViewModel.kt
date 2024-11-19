package com.example.training_android

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.training_android.Model.Message
import com.example.training_android.Retrofit.RequestBody
import com.example.training_android.Retrofit.RetrofitInstance
import com.example.training_android.Room.Favorite
import com.example.training_android.Room.FavoriteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

private const val TAG = "MainViewModel"

class MainViewModel(
    private val dataStore: FavoritesDataStore,
    private val dao: FavoriteDao
): ViewModel() {

    var number = mutableStateOf(0)
    var maxNumber = mutableStateOf(0)
    var chatTextField = mutableStateOf("")
    var responseMessage = mutableStateOf("")

    var messageList = mutableStateOf<List<Message>>(listOf(Message("Merhaba", false), Message("Selam", true)))

    var favoriteList = mutableStateOf<List<Favorite>>(emptyList())

    init {
        viewModelScope.launch {
            maxNumber.value = dataStore.getNumber()

            favoriteList.value = dao.getFavorites()
        }
    }

    fun updateValue() {
        number.value += 1

        if (number.value > maxNumber.value) {
            saveNumber(number.value)
        }
    }

    fun resetNumber() {
        number.value = 0
    }

    private fun saveNumber(number: Int) {
        viewModelScope.launch {
            dataStore.saveNumber(number)

            maxNumber.value = dataStore.getNumber()
        }
    }

    fun addFavorites(number: Int) {
        viewModelScope.launch {
            dao.insertFavorite(Favorite(number))

            favoriteList.value = dao.getFavorites()
        }
    }

    fun clearFavorites() {
        viewModelScope.launch {
            dao.deleteAllFavorites()

            favoriteList.value = dao.getFavorites()
        }
    }

    fun sendRequest(message: String) {
        messageList.value += Message(message, true)
        chatTextField.value = ""

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.chatApi.get(RequestBody("sophaAI", message))
                messageList.value += Message(response.msg, false)
            } catch (e: Exception) {

            }
        }
    }
}