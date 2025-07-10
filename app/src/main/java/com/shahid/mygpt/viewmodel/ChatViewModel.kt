package com.shahid.mygpt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahid.mygpt.data.ChatMessage
import com.shahid.mygpt.network.GeminiApiClient
import com.shahid.mygpt.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    
    private val repository = ChatRepository()
    
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _apiKey = MutableStateFlow("YOUR_API_KEY")
    val apiKey: StateFlow<String> = _apiKey.asStateFlow()
    
    // Model selection - you can change this to use different models
    private val _selectedModel = MutableStateFlow(GeminiApiClient.Models.GEMINI_2_0_FLASH)
    val selectedModel: StateFlow<String> = _selectedModel.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    fun setApiKey(key: String) {
        _apiKey.value = key
    }
    
    fun setModel(model: String) {
        _selectedModel.value = model
    }
    
    fun sendMessage(message: String) {
        if (message.isBlank()) return
        
        val userMessage = ChatMessage(
            content = message.trim(),
            isFromUser = true
        )
        
        // Add user message to the list
        _messages.value = _messages.value + userMessage
        
        // Add typing indicator
        val typingMessage = ChatMessage(
            content = "",
            isFromUser = false,
            isTyping = true
        )
        _messages.value = _messages.value + typingMessage
        
        _isLoading.value = true
        _errorMessage.value = null
        
        viewModelScope.launch {
            try {
                val result = repository.sendMessage(
                    message = message,
                    apiKey = _apiKey.value,
                    model = _selectedModel.value,
                    conversationHistory = _messages.value.filter { !it.isTyping }
                )
                
                result.fold(
                    onSuccess = { reply ->
                        // Remove typing indicator and add AI response
                        val messagesWithoutTyping = _messages.value.filter { !it.isTyping }
                        val aiMessage = ChatMessage(
                            content = reply,
                            isFromUser = false
                        )
                        _messages.value = messagesWithoutTyping + aiMessage
                    },
                    onFailure = { exception ->
                        // Remove typing indicator
                        _messages.value = _messages.value.filter { !it.isTyping }
                        _errorMessage.value = when {
                            exception.message?.contains("401") == true -> 
                                "Invalid API key. Please check your Gemini API key."
                            exception.message?.contains("network") == true -> 
                                "Network error. Please check your internet connection."
                            else -> "Error: ${exception.message ?: "Unknown error occurred"}"
                        }
                    }
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun clearMessages() {
        _messages.value = emptyList()
        _errorMessage.value = null
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
    
    fun validateApiKey(): Boolean {
        return repository.validateApiKey(_apiKey.value)
    }
} 