package com.stanley.mindbridge.ui.screens.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.stanley.mindbridge.navigation.ROUT_APPOINTMENTBACK
import com.stanley.mindbridge.navigation.ROUT_JOURNALVIEW
import com.stanley.mindbridge.navigation.ROUT_LOGIN
import com.stanley.mindbridge.navigation.ROUT_START

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Admin Dashboard") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUT_LOGIN) {
                            popUpTo("target_screen_route") { inclusive = false }
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Overview",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(title = "Active Users", value = "1,240")
                StatCard(title = "Sessions Today", value = "320")
                StatCard(title = "Flagged Messages", value = "12")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Navigation Card
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavigationCard(
                        title = "Appointments Made",
                        description = "",
                        onClick = { navController.navigate(ROUT_APPOINTMENTBACK) }
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    NavigationCard(
                        title = "JOURNALS",
                        description = "",
                        onClick = { navController.navigate(ROUT_JOURNALVIEW) }
                    )
                }
            }



            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Recent Flagged Messages",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val flaggedMessages = listOf(
                "Crisis content detected",
                "Inappropriate language",
                "Self-harm keywords"
            )

            flaggedMessages.forEach { reason ->
                FlaggedMessageItem(user = "User XYZ", reason = reason)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun StatCard(title: String, value: String) {
    Card(
        modifier = Modifier.width(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontSize = 14.sp, color = Color.Gray)
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FlaggedMessageItem(user: String, reason: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = user, fontWeight = FontWeight.SemiBold)
                Text(text = reason, color = Color(0xFFD84315), fontSize = 13.sp)
            }
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = Color(0xFFD84315)
            )
        }
    }
}

@Composable
fun NavigationCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(width = 150.dp, height =90.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD1C4E9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 9.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminDashboardScreenPreview() {
    AdminDashboardScreen(navController = rememberNavController())
}
