package com.shahid.mygpt.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.shahid.mygpt.data.ChatMessage

@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    onSendMessage: (String) -> Unit,
    isLoading: Boolean,
    errorMessage: String?,
    onClearError: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    
    // Auto-scroll to bottom when new messages arrive
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }
    // Animated gradient background (using animateFloat and lerp for compatibility)
    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val gradientStart = listOf(
        Color(0xFFF8FBFF),
        Color(0xFFE8ECF3),
        Color(0xFFF8FBFF)
    )
    val gradientEnd = listOf(
        Color(0xFFE0E7FF),
        Color(0xFFB3C6FF),
        Color(0xFFF8FBFF)
    )
    val animatedColors = gradientStart.zip(gradientEnd) { start, end ->
        lerp(start, end, progress)
    }
    val gradient = Brush.verticalGradient(colors = animatedColors)
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        // Animated welcome banner
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(1200)) + slideInVertically(
                initialOffsetY = { -40 },
                animationSpec = tween(1200, easing = FastOutSlowInEasing)
            )
        ) {
            Surface(
                color = Color(0xFFEDF4FF).copy(alpha = 0.85f),
                shadowElevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "👋 Welcome! How can I help you today?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF4A4A68),
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)
                )
            }
        }
        // Chat messages
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(messages) { message ->
                ChatMessageItem(message = message)
            }
        }
        // Error message
        errorMessage?.let { error ->
            ErrorBanner(
                message = error,
                onDismiss = onClearError
            )
        }
        // Input section
        ChatInput(
            onSendMessage = onSendMessage,
            isLoading = isLoading,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ErrorBanner(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFE5E5)
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = Color(0xFFD32F2F),
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = message,
                color = Color(0xFFD32F2F),
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Dismiss",
                    color = Color(0xFFD32F2F)
                )
            }
        }
    }
} 