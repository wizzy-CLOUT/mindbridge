package com.stanley.mindbridge.ui.theme.screens.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Calendar

import com.stanley.mindbridge.model.Contact
import com.stanley.mindbridge.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadAppointmentScreen(
    navController: NavController,
    contentViewModel: ContactViewModel,
    editingContentId: Int? = null
) {

    //Scaffold

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("Upload Content") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back/nav */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        //BottomBar
        bottomBar = {
            NavigationBar(
                containerColor = Color.LightGray
            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                        //navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        // navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        //  navController.navigate(ROUT_HOME)
                    }
                )

            }
        },

        //FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = Color.LightGray
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {


                //Main Contents of the page

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

                Column(Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = subject,
                        onValueChange = { subject = it },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = message,
                        onValueChange = { message = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth()
                    )



                    // Date and Time Picker

                    val context = LocalContext.current

                    Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                        Button(
                            onClick = {
                                val calendar = Calendar.getInstance()
                                val year = calendar.get(Calendar.YEAR)
                                val month = calendar.get(Calendar.MONTH)
                                val day = calendar.get(Calendar.DAY_OF_MONTH)

                                android.app.DatePickerDialog(
                                    context,
                                    { _, selectedYear, selectedMonth, selectedDay ->
                                        val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"

                                        // TimePicker inside DatePicker callback
                                        val hour = calendar.get(Calendar.HOUR_OF_DAY)
                                        val minute = calendar.get(Calendar.MINUTE)

                                        android.app.TimePickerDialog(
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
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.Gray),
                            modifier = Modifier
                                .height(65.dp)
                                .padding(top = 10.dp)
                        ) {
                            Text(text = "Date & Time")
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        OutlinedTextField(
                            value = appointmentDate,
                            onValueChange = { /* No-op */ },
                            label = { Text("Select") },
                            readOnly = true,
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                                .width(250.dp),
                            trailingIcon = {
                                Text(text = "📅")
                            },
                            singleLine = true
                        )
                    }


                    //End of a datefield



                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
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
                    }) {
                        Text(if (editingContent != null) "Update Content" else "Upload Content")
                    }
                }




            }
        }
    )

    //End of scaffold

}