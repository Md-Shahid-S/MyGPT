package com.shahid.mygpt.repository

import com.shahid.mygpt.data.*
import com.shahid.mygpt.network.GeminiApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class ChatRepository {
    
    suspend fun sendMessage(
        message: String,
        apiKey: String,
        model: String = GeminiApiClient.Models.GEMINI_2_0_FLASH,
        conversationHistory: List<ChatMessage>
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            // Build conversation context from history
            val conversationText = conversationHistory
                .takeLast(10) // Keep last 10 messages for context
                .joinToString("\n") { "${if (it.isFromUser) "User" else "Assistant"}: ${it.content}" }
            
            val fullPrompt = if (conversationText.isNotEmpty()) {
                "$conversationText\nUser: $message"
            } else {
                message
            }
            
            val request = GeminiRequest(
                contents = listOf(
                    Content(
                        parts = listOf(
                            Part(text = fullPrompt)
                        )
                    )
                ),
                generationConfig = GenerationConfig(
                    temperature = 0.7,
                    maxOutputTokens = 2048
                )
            )
            
            val response = GeminiApiClient.apiService.generateContent(
                model = model,
                apiKey = apiKey,
                request = request
            )
            
            val reply = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                ?: throw Exception("No response from Gemini API")
            
            Result.success(reply)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            Result.failure(Exception("HTTP ${e.code()}: $errorBody"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun validateApiKey(apiKey: String): Boolean {
        return apiKey.isNotBlank() && apiKey.length > 10
    }
} 