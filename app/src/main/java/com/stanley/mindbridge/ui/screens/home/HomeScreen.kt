package com.stanley.mindbridge.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.stanley.mindbridge.R
import com.stanley.mindbridge.navigation.ROUT_APPOINTMENT
import com.stanley.mindbridge.navigation.ROUT_BREATHINGEXERCISESCREEN
import com.stanley.mindbridge.navigation.ROUT_JOURNAL
import com.stanley.mindbridge.navigation.ROUT_JOURNALVIEW
import com.stanley.mindbridge.navigation.ROUT_LOGIN
import com.stanley.mindbridge.navigation.ROUT_TRACKER
import com.stanley.mindbridge.ui.theme.newpurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = newpurple

    ) {

        padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
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
                    painter = painterResource(id = R.drawable.img_1),
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

                // ✅ Buttons now navigate
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    HomeActionButton("Mind Reset", Icons.Default.Refresh) {
                       navController.navigate(ROUT_BREATHINGEXERCISESCREEN) // <-- Replace with your actual route
                }
                    HomeActionButton("Journaling", Icons.Default.Edit) {
                        navController.navigate(ROUT_JOURNAL) // <-- Replace with your actual route
                    }
                   // HomeActionButton("Mood Tracker", Icons.Default.Face) {
                     //   navController.navigate(ROUT_TRACKER) // <-- Replace with your actual route
                   // }
                    HomeActionButton("Make Appointment", Icons.AutoMirrored.Filled.Send) {
                        navController.navigate(ROUT_APPOINTMENT) // <-- Replace with your actual route
                    }
                    HomeActionButton(" View Past Journals", Icons.Default.Refresh) {
                        navController.navigate(ROUT_JOURNALVIEW) // <-- Replace with your actual route
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun HomeActionButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit // ✅ Add click handler
) {
    Button(
        onClick = onClick,
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
