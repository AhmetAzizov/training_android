package com.example.training_android

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.training_android.Model.Message
import com.example.training_android.Retrofit.RequestBody
import com.example.training_android.Retrofit.RetrofitInstance
import com.example.training_android.Room.Favorite
import com.example.training_android.Room.FavoriteDao
import kotlinx.coroutines.launch

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
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.chatApi.get(RequestBody("sophaAI", message))
                messageList.value += Message(response.msg, false)
//                val text = "CODE: ${response.code} \n\nMESSAGE: ${response.msg}"
//                responseMessage.value = text
            } catch (e: Exception) {

                responseMessage.value = e.message.toString()

            }
        }
    }
}