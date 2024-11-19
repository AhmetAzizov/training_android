package com.example.training_android.Retrofit

import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {

    @POST("/chat")
    suspend fun get(@Body body: RequestBody): ResponseBody
}