package com.stanley.mindbridge.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Refresh
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
import com.stanley.mindbridge.ui.theme.newpurple

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = newpurple
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // enables scrolling
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to MindBridge",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Your mental well-being matters",
                    fontSize = 18.sp,
                    color = Color.White.copy(alpha = 0.85f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Image(
                    painter = painterResource(id = R.drawable.img_1), // Replace with your image
                    contentDescription = "Calm illustration",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "“You don’t have to control your thoughts. You just have to stop letting them control you.”",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = newpurple
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "- Dan Millman",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    HomeActionButton("Mind Reset", Icons.Default.Refresh)
                    HomeActionButton("Journaling", Icons.Default.Edit)
                    HomeActionButton("Mood Tracker", Icons.Default.Face)
                    HomeActionButton("Contact Support", Icons.AutoMirrored.Filled.Send)
                }

                Spacer(modifier = Modifier.height(16.dp)) // Extra spacing at bottom
            }
        }
    }
}

@Composable
fun HomeActionButton(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Button(
        onClick = { /* TODO: Add navigation logic */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(icon, contentDescription = null, tint = newpurple)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = newpurple,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
