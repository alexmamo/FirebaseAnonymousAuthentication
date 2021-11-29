package ro.alexmamo.firebaseanonymousauthentication.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.AuthScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.profile.ProfileScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.util.Screen.AuthScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.util.Screen.ProfileScreen

@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = AuthScreen.route
            ) {
                composable(
                    route = AuthScreen.route
                ) {
                    AuthScreen(
                        navController = navController
                    )
                }
                composable(
                    route = ProfileScreen.route
                ) {
                    ProfileScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}