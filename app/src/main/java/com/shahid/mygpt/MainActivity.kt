package com.shahid.mygpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shahid.mygpt.ui.components.ChatScreen
import com.shahid.mygpt.ui.components.SetupScreen
import com.shahid.mygpt.ui.theme.MyGPTTheme
import com.shahid.mygpt.viewmodel.ChatViewModel

class MainActivity : ComponentActivity() {
    
    private val viewModel: ChatViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            MyGPTTheme {
                ChatApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatApp(
    viewModel: ChatViewModel,
    modifier: Modifier = Modifier
) {
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val apiKey by viewModel.apiKey.collectAsStateWithLifecycle()
    
    if (apiKey.isBlank()) {
        // Show setup screen
        SetupScreen(
            onApiKeySet = { key ->
                viewModel.setApiKey(key)
            },
            modifier = modifier
        )
    } else {
        // Show chat screen
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "MyGPT",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        ) { innerPadding ->
            ChatScreen(
                messages = messages,
                onSendMessage = { message ->
                    viewModel.sendMessage(message)
                },
                isLoading = isLoading,
                errorMessage = errorMessage,
                onClearError = {
                    viewModel.clearError()
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatAppPreview() {
    MyGPTTheme {
        // Preview placeholder
        Text("Chat App Preview")
    }
}