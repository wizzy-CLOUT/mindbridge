package com.stanley.mindbridge.ui.theme.screens.content

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import com.stanley.mindbridge.model.Contact
import com.stanley.mindbridge.navigation.ROUT_HOME
import com.stanley.mindbridge.viewmodel.ContactViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadAppointmentScreen(
    navController: NavController,
    contentViewModel: ContactViewModel,
    editingContentId: Int? = null
) {
    var selectedIndex by remember { mutableStateOf(0) }

    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var appointmentDate by remember { mutableStateOf("") }

    LaunchedEffect(editingContentId) {
        if (editingContentId != null) {
            contentViewModel.loadContactById(editingContentId)
        }
    }

    val editingContent = contentViewModel.selectedContact.collectAsState().value

    LaunchedEffect(editingContent) {
        editingContent?.let {
            subject = it.subject
            message = it.message
            appointmentDate = it.appointmentDate
        }
    }

    val isFormValid = subject.isNotBlank() && message.isNotBlank() && appointmentDate.isNotBlank()

    Scaffold(
        containerColor = Color(0xFFF5F5F5),
        topBar = {
            TopAppBar(
                title = { Text("Make Appointment") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = ROUT_HOME)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50),
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
                        OutlinedTextField(
                            value = subject,
                            onValueChange = { subject = it },
                            label = { Text("Patient Name") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        )

                        Spacer(Modifier.height(12.dp))

                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            label = { Text("How may we help?") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        )

                        Spacer(Modifier.height(16.dp))

                        val context = LocalContext.current

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
                                                    val selectedTime =
                                                        String.format("%02d:%02d", selectedHour, selectedMinute)
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
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784)),
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
                                val contact = Contact(
                                    id = editingContent?.id ?: 0,
                                    subject = subject,
                                    message = message,
                                    appointmentDate = appointmentDate,
                                )
                                if (editingContent != null) {
                                    contentViewModel.update(contact)
                                } else {
                                    contentViewModel.insert(contact)
                                }
                                navController.popBackStack()
                            },
                            enabled = isFormValid,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isFormValid) Color(0xFF4CAF50) else Color(0xFFBDBDBD)
                            )
                        ) {
                            Text(
                                if (editingContent != null) "Update Content" else "Upload Content",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    )
}
