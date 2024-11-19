package com.example.training_android.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://support.matlubapps.com"

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val chatApi: ChatApi by lazy {
        retrofit.create(ChatApi::class.java)
    }
}