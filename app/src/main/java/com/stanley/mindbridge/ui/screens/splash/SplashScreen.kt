package com.stanley.mindbridge.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.stanley.mindbridge.R
import com.stanley.mindbridge.navigation.ROUT_LOGIN
import com.stanley.mindbridge.navigation.ROUT_START
import com.stanley.mindbridge.ui.theme.newpurple
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Launch effect only once during composition
    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds delay for calm effect
        navController.navigate(ROUT_START) {
            popUpTo(0) // Clear back stack
        }
    }

    // Splash UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(newpurple)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Icon / Logo
        Image(
            painter = painterResource(R.drawable.img_3),
            contentDescription = "App Logo",
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Title
        Text(
            text = "Own your mental state",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))


        Spacer(modifier = Modifier.height(40.dp))

        // Optional subtitle
        Text(
            text = "Preparing your mindful journey...",
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
