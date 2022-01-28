package ro.alexmamo.firebaseanonymousauthentication.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.AuthScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.navigation.Screen.AuthScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.navigation.Screen.ProfileScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.profile.ProfileScreen

@Composable
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@OptIn(ExperimentalAnimationApi::class)
fun NavGraph (
    navController: NavHostController
) {
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