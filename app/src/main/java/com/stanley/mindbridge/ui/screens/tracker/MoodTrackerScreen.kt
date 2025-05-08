package com.stanley.mindbridge.ui.screens.tracker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stanley.mindbridge.ui.theme.newpurple
import androidx.compose.ui.platform.LocalContext

@Composable
fun MoodTrackerScreen(navController: NavHostController) {
    // States for user mood inputs
    var moodRating by remember { mutableStateOf(3) }  // Rating scale from 1 to 5
    var stressLevel by remember { mutableStateOf(3) }  // Stress level rating
    var reflectionNotes by remember { mutableStateOf("") }  // User's thoughts
    var moodSaved by remember { mutableStateOf(false) }  // To display saved status

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B5E20))  // Dark green background
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Mood Tracker",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))  // Space between title and question

        // Mood Rating Question
        Text(
            text = "How would you rate your mood today?",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mood Rating Scale (1 - 5)
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Happy Face Icon
            Icon(
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "Sad",
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .clickable {
                        moodRating = 1
                    },
                tint = if (moodRating == 1) Color.Red else Color.Gray
            )
            for (i in 2..5) {
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = "Mood Rating",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                        .clickable {
                            moodRating = i
                        },
                    tint = if (moodRating == i) Color.Green else Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))  // Space before stress level question

        // Stress Level Question
        Text(
            text = "How stressed do you feel?",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stress Level Rating (1 - 5)
        Slider(
            value = stressLevel.toFloat(),
            onValueChange = { stressLevel = it.toInt() },
            valueRange = 1f..5f,
            steps = 4,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                activeTrackColor = newpurple,
                inactiveTrackColor = Color.Gray,
                thumbColor = newpurple
            )
        )

        Spacer(modifier = Modifier.height(24.dp))  // Space before reflection notes

        // Reflection Notes Section
        OutlinedTextField(
            value = reflectionNotes,
            onValueChange = { reflectionNotes = it },
            placeholder = { Text("Any thoughts you'd like to reflect on?") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            maxLines = 6,

        )

        Spacer(modifier = Modifier.height(32.dp))  // Space before save button

        // Save Button
        Button(
            onClick = {
                moodSaved = true  // Mark mood as saved
                // Here, you could save the mood information to a database or server
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = newpurple),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Save Mood",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        // Display saved status with reflection notes
        if (moodSaved) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Mood Saved for Today",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Display reflection notes if provided
            if (reflectionNotes.isNotEmpty()) {
                Text(
                    text = "Your Reflection Notes:",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = reflectionNotes,
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoodTrackerScreenPreview() {
    MoodTrackerScreen(navController = NavHostController(context = LocalContext.current))  // Just a preview for context
}
