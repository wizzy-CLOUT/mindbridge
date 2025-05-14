package com.stanley.mindbridge.ui.theme.screens.content

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stanley.mindbridge.model.Journal
import com.stanley.mindbridge.navigation.ROUT_HOME
import com.stanley.mindbridge.viewmodel.JournalViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadJournalScreen(
    navController: NavController,
    JournalViewModel: JournalViewModel,
    editingJournalId: Int? = null
) {
    var selectedIndex by remember { mutableStateOf(0) }

    val context = LocalContext.current
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var appointmentDate by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    val isMessageValid = message.trim().isNotEmpty()

    // Load journal if editing
    LaunchedEffect(editingJournalId) {
        if (editingJournalId != null) {
            JournalViewModel.loadJournalById(editingJournalId)
        }
    }

    val editingJournal = JournalViewModel.selectedJournal.collectAsState().value

    LaunchedEffect(editingJournal) {
        editingJournal?.let {
            message = it.message
        }
    }

    Scaffold(
        containerColor = Color(0xFF004603),
        topBar = {
            TopAppBar(
                title = { Text("Upload Journal") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = ROUT_HOME)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004603),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFE0E0E0)) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = ROUT_HOME) },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color(0xFFF5F5F5))
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {


                        Spacer(Modifier.height(12.dp))

                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            label = { Text("Description") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        )

                        Spacer(Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {
                                    val calendar = Calendar.getInstance()
                                    val year = calendar.get(Calendar.YEAR)
                                    val month = calendar.get(Calendar.MONTH)
                                    val day = calendar.get(Calendar.DAY_OF_MONTH)

                                    DatePickerDialog(
                                        context,
                                        { _, selectedYear, selectedMonth, selectedDay ->
                                            val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                                            val hour = calendar.get(Calendar.HOUR_OF_DAY)
                                            val minute = calendar.get(Calendar.MINUTE)

                                            TimePickerDialog(
                                                context,
                                                { _, selectedHour, selectedMinute ->
                                                    val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                                                    appointmentDate = "$selectedDate $selectedTime"
                                                },
                                                hour,
                                                minute,
                                                true
                                            ).show()
                                        },
                                        year,
                                        month,
                                        day
                                    ).show()
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004603)),
                                modifier = Modifier
                                    .height(56.dp)
                                    .weight(0.4f)
                            ) {
                                Text("Date & Time", color = Color.White)
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            OutlinedTextField(
                                value = appointmentDate,
                                onValueChange = {},
                                label = { Text("Select") },
                                readOnly = true,
                                modifier = Modifier
                                    .height(56.dp)
                                    .weight(0.6f),
                                trailingIcon = { Text("📅") },
                                singleLine = true,
                                shape = RoundedCornerShape(8.dp)
                            )
                        }

                        Spacer(Modifier.height(24.dp))

                        Button(
                            onClick = {
                                if (isSubmitted) {
                                    Toast.makeText(context, "This journal has already been submitted", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                if (isMessageValid) {
                                    val journal = Journal(
                                        id = editingJournal?.id ?: 0,
                                        message = message.trim()

                                    )
                                    if (editingJournal != null) {
                                        JournalViewModel.update(journal)
                                    } else {
                                        JournalViewModel.insert(journal)
                                    }

                                    isSubmitted = true
                                    navController.popBackStack()
                                } else {
                                    Toast.makeText(context, "Message cannot be empty", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004603)),
                            enabled = isMessageValid && !isSubmitted
                        ) {
                            Text(
                                if (editingJournal != null) "Update Content" else "Upload Content",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    )
}
