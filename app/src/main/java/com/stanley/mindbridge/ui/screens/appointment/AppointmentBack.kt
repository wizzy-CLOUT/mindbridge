package com.stanley.mindbridge.ui.theme.screens.content



import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stanley.mindbridge.R
import com.stanley.mindbridge.model.Contact
import com.stanley.mindbridge.viewmodel.ContactViewModel

import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ViewContentScreen(
    navController: NavController,
    contactViewModel: ContactViewModel,
    onEdit: (Int) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val contentList by contactViewModel.allContact.collectAsState(initial = emptyList())

    // Auto-slide carousel logic
    val carouselImages = listOf(R.drawable.img_3, R.drawable.img_3, R.drawable.img_3)
    var currentImageIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // 1 second
            currentImageIndex = (currentImageIndex + 1) % carouselImages.size
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Content") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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

        bottomBar = {
            NavigationBar(containerColor = Color.LightGray) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("upload_content") },
                containerColor = Color.LightGray
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
                .fillMaxSize()
        ) {
            // Auto-Scrolling Carousel
            Text(
                text = "Featured",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Image(
                    painter = painterResource(id = carouselImages[currentImageIndex]),
                    contentDescription = "Carousel Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Content Grid Section
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(contentList.size) { index ->
                    val contact = contentList[index]
                    val backgroundColor = if (index % 2 == 0) Color.LightGray else Color(0xFFD0E8F2) // Light Blue

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp), // 👈 uniform card height
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = backgroundColor)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.SpaceBetween // 👈 balance content vertically
                        ) {
                            Column {
                                Text(
                                    text = "Subject: ${contact.subject}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = "How may we be of help: ${contact.message}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    maxLines = 3 // 👈 optional: keep things neat
                                )
                                Text(
                                    text = ": ${contact.appointmentDate}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    maxLines = 3 // 👈 optional: keep things neat
                                )
                            }


                        }
                    }
                }
            }


        }
    }
}