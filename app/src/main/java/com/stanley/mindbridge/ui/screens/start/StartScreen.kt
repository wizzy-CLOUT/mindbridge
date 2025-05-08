package com.stanley.mindbridge.ui.screens.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.stanley.mindbridge.navigation.ROUT_REGISTER
import com.stanley.mindbridge.ui.theme.newpurple

@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(newpurple)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.img_3),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(180.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Title
        Text(
            text = "Take a slow, deep breath",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Text(
            text = "Reset your mind",
            color = Color.White.copy(alpha = 0.85f),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Sign Up Button
        Button(
            onClick = { navController.navigate(ROUT_REGISTER) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(
                text = "Sign Up",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Existing user prompt
        Text(
            text = "Already have an account?",
            color = Color.White.copy(alpha = 0.7f),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Log In Button
        Button(
            onClick = { navController.navigate(ROUT_LOGIN) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Log In",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    StartScreen(navController = rememberNavController())
}
