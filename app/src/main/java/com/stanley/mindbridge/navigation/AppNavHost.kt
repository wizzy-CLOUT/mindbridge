package com.stanley.mindbridge.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.Stanley.sokomart.ui.screens.auth.LoginScreen
import com.example.mentalhealthapp.ui.BreathingExerciseScreen
import com.stanley.mindbridge.data.ContactDatabase
import com.stanley.mindbridge.data.UserDatabase
import com.stanley.mindbridge.repository.ContactRepository
import com.stanley.mindbridge.repository.UserRepository
import com.stanley.mindbridge.ui.screens.about.AboutScreen
import com.stanley.mindbridge.ui.screens.about.HomeScreen
import com.stanley.mindbridge.ui.screens.auth.RegisterScreen
import com.stanley.mindbridge.ui.screens.journal.JournalScreen
import com.stanley.mindbridge.ui.screens.splash.SplashScreen
import com.stanley.mindbridge.ui.screens.start.StartScreen
import com.stanley.mindbridge.ui.screens.tracker.MoodTrackerScreen
import com.stanley.mindbridge.ui.theme.screens.content.UploadAppointmentScreen
import com.stanley.mindbridge.ui.theme.screens.content.ViewContentScreen
import com.stanley.mindbridge.viewmodel.AuthViewModel
import com.stanley.mindbridge.viewmodel.ContactViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_APPOINTMENT
) {
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_START) {
            StartScreen(navController)
        }
        composable(ROUT_JOURNAL) {
            JournalScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_TRACKER) {
            MoodTrackerScreen(navController)
        }
        composable(ROUT_BREATHINGEXERCISESCREEN) {
            BreathingExerciseScreen(navController)
        }

        //CONTENT

        // Initialize Content Database and ViewModel
        val contentDatabase = ContactDatabase.getDatabase(context)
        val contentRepository = ContactRepository(contentDatabase.ContactDao())
        val contentViewModel = ContactViewModel(contentRepository)

        composable(ROUT_APPOINTMENT) {
            UploadAppointmentScreen(navController, contentViewModel)
        }
        composable(ROUT_APPOINTMENTBACK) {
            ViewContentScreen(navController, contentViewModel) { id ->
                navController.navigate("upload_content?id=$id")
            }
        }


        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }
        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }









    }
}