package ro.alexmamo.firebaseanonymousauthentication.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.AuthScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.profile.ProfileScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.util.Screen.AuthScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.util.Screen.ProfileScreen

@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            AnimatedNavHost(
                navController = navController,
                startDestination = AuthScreen.route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
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