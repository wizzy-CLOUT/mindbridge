package com.stanley.mindbridge.ui.screens.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stanley.mindbridge.ui.theme.newpurple

@Composable
fun JournalScreen(navController: NavHostController) {
    var journalEntry by remember { mutableStateOf("") }
    var savedEntry by remember { mutableStateOf("") }

    // Using a dark green background
    val darkGreen = Color(0xFF1B5E20)  // Dark Green color

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGreen)  // Dark green background
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title with light font color for contrast
        Text(
            text = "Daily Journal",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))  // Space between title and text field

        // TextField to write journal entry
        OutlinedTextField(
            value = journalEntry,
            onValueChange = { journalEntry = it },
            placeholder = { Text("Write your thoughts here...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
                .background(Color.White, RoundedCornerShape(12.dp))  // Paper-like background
                .shadow(5.dp, RoundedCornerShape(12.dp)),  // Adding shadow for depth
            shape = RoundedCornerShape(12.dp),
            maxLines = 10,

        )

        Spacer(modifier = Modifier.height(32.dp))  // Space after text field

        // Save Button
        Button(
            onClick = {
                savedEntry = journalEntry
                journalEntry = "" // Clear the input field after saving
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = newpurple),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Save Entry",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        // Display saved journal entry
        if (savedEntry.isNotEmpty()) {
            Spacer(modifier = Modifier.height(48.dp))  // Space before saved entry section
            Text(
                text = "Last Entry:",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Card to display saved entry
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F0F0)),  // Light paper color
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)  // Padding for the card
                    .shadow(8.dp, RoundedCornerShape(12.dp))  // Card shadow for depth
            ) {
                Text(
                    text = savedEntry,
                    modifier = Modifier.padding(16.dp),
                    color = newpurple,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JournalScreenPreview() {
    JournalScreen(navController = NavHostController(context = LocalContext.current))  // Just a preview for context
}
