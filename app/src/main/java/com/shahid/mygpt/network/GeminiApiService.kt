package com.shahid.mygpt.network

import com.shahid.mygpt.data.GeminiRequest
import com.shahid.mygpt.data.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface GeminiApiService {
    @POST("v1beta/models/{model}:generateContent")
    suspend fun generateContent(
        @Path("model") model: String,
        @Header("x-goog-api-key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}

object GeminiApiClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"
    
    // Available models
    object Models {
        const val GEMINI_1_5_PRO = "gemini-1.5-pro"
        const val GEMINI_1_5_FLASH = "gemini-1.5-flash"
        const val GEMINI_2_0_PRO = "gemini-2.0-pro"
        const val GEMINI_2_0_FLASH = "gemini-2.0-flash"
        const val GEMINI_PRO = "gemini-pro"
        const val GEMINI_PRO_VISION = "gemini-pro-vision"
    }
    
    private val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build()
    
    val apiService: GeminiApiService = retrofit.create(GeminiApiService::class.java)
} 