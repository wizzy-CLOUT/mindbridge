package com.example.mentalhealthapp.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun BreathingExerciseScreen(navController: NavHostController) {
    var phase by remember { mutableStateOf("Breathe In") }

    val infiniteTransition = rememberInfiniteTransition()
    val size by infiniteTransition.animateFloat(
        initialValue = 100f,
        targetValue = 250f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Breathing cycle logic
    LaunchedEffect(Unit) {
        while (true) {
            phase = "Breathe In"
            delay(5000)
            phase = "Hold"
            delay(2500)
            phase = "Breathe Out"
            delay(5000)
            phase = "Hold"
            delay(2500)
        }
    }

    BreathingExerciseUIDarkTheme(phase = phase, size = size)
}

@Composable
fun BreathingExerciseUIDarkTheme(phase: String, size: Float) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF1B4332), Color(0xFF081C15)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = phase,
                color = Color(0xFF95D5B2),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))



            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Inhale... Hold... Exhale... Hold...",
                fontSize = 18.sp,
                color = Color(0xFFA3B18A),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BreathingExercisePreviewDarkTheme() {
    BreathingExerciseUIDarkTheme(
        phase = "Breathe In",
        size = 180f // Example size for preview
    )
}
