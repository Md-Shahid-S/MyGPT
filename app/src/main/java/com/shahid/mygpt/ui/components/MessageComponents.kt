package com.shahid.mygpt.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahid.mygpt.data.ChatMessage
import kotlinx.coroutines.delay

@Composable
fun ChatMessageItem(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    val isUser = message.isFromUser
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(600)) + slideInHorizontally(
            initialOffsetX = { if (isUser) 120 else -120 },
            animationSpec = tween(600, easing = FastOutSlowInEasing)
        )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            if (!isUser) {
                // AI avatar
                AvatarBubble(isUser = false)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Column(horizontalAlignment = if (isUser) Alignment.End else Alignment.Start) {
                if (message.isTyping) {
                    TypingIndicator()
                } else {
                    MessageBubble(
                        content = message.content,
                        isFromUser = isUser,
                        timestamp = message.getFormattedTime()
                    )
                }
            }
            if (isUser) {
                Spacer(modifier = Modifier.width(8.dp))
                // User avatar
                AvatarBubble(isUser = true)
            }
        }
    }
}

@Composable
fun AvatarBubble(isUser: Boolean) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(if (isUser) Color(0xFF4A90E2) else Color(0xFFB388FF)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isUser) "🧑" else "🤖",
            fontSize = 20.sp
        )
    }
}

@Composable
fun MessageBubble(
    content: String,
    isFromUser: Boolean,
    timestamp: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isFromUser) Color(0xFF4A90E2) else Color(0xFFF1F3F6)
    val textColor = if (isFromUser) Color.White else Color(0xFF222244)
    val shape = if (isFromUser) {
        RoundedCornerShape(20.dp, 20.dp, 4.dp, 20.dp)
    } else {
        RoundedCornerShape(20.dp, 20.dp, 20.dp, 4.dp)
    }
    Column(
        modifier = modifier.widthIn(max = 280.dp),
        horizontalAlignment = if (isFromUser) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .shadow(4.dp, shape)
                .clip(shape)
                .background(backgroundColor)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            if (isFromUser) {
                Text(
                    text = content,
                    color = textColor,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            } else {
                // Custom bot reply rendering: bullet points and keyword highlighting
                val lines = content.lines()
                Column {
                    lines.forEach { line ->
                        when {
                            line.trim().startsWith("-") || line.trim().startsWith("*") -> {
                                Row(verticalAlignment = Alignment.Top) {
                                    Text("• ", color = Color(0xFF4A90E2), fontWeight = FontWeight.Bold)
                                    Text(
                                        text = line.trim().removePrefix("-").removePrefix("*").trim(),
                                        color = textColor,
                                        fontSize = 16.sp,
                                        maxLines = 5,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                            line.contains("Important:", ignoreCase = true) -> {
                                Text(
                                    text = line,
                                    color = Color(0xFFD32F2F),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                            else -> {
                                Text(
                                    text = line,
                                    color = textColor,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = timestamp,
            fontSize = 12.sp,
            color = Color(0xFF8A8A99),
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Composable
fun TypingIndicator(
    modifier: Modifier = Modifier
) {
    val dotCount = 3
    val delays = listOf(0, 150, 300)
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF1F3F6))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until dotCount) {
            val anim = remember { Animatable(0f) }
            LaunchedEffect(Unit) {
                while (true) {
                    delay(delays[i].toLong())
                    anim.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 400, easing = LinearEasing)
                    )
                    anim.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 400, easing = LinearEasing)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(10.dp + 4.dp * anim.value)
                    .clip(CircleShape)
                    .background(Color(0xFFB388FF).copy(alpha = 0.7f))
            )
        }
    }
} 