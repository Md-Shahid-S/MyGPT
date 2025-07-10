package com.shahid.mygpt.data

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

// Chat message data class
data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val isFromUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val isTyping: Boolean = false
) {
    fun getFormattedTime(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }
}

// Gemini API request models
data class GeminiRequest(
    val contents: List<Content>,
    val generationConfig: GenerationConfig? = null,
    val safetySettings: List<SafetySetting>? = null
)

data class Content(
    val parts: List<Part>
)

data class Part(
    val text: String
)

data class GenerationConfig(
    val temperature: Double = 0.7,
    val topK: Int = 40,
    val topP: Double = 0.95,
    val maxOutputTokens: Int = 2048
)

data class SafetySetting(
    val category: String,
    val threshold: String
)

// Gemini API response models
data class GeminiResponse(
    val candidates: List<Candidate>?,
    val promptFeedback: PromptFeedback? = null
)

data class Candidate(
    val content: Content,
    val finishReason: String?,
    val index: Int,
    val safetyRatings: List<SafetyRating>? = null
)

data class SafetyRating(
    val category: String,
    val probability: String
)

data class PromptFeedback(
    val safetyRatings: List<SafetyRating>? = null
)

// API Error response
data class ApiError(
    val error: ErrorDetails
)

data class ErrorDetails(
    val code: Int,
    val message: String,
    val status: String
) 