package ro.alexmamo.firebaseanonymousauthentication.presentation.auth

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.alexmamo.firebaseanonymousauthentication.core.Constants.TAG
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.*
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthContent
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthTopBar
import ro.alexmamo.firebaseanonymousauthentication.presentation.components.ProgressBar
import ro.alexmamo.firebaseanonymousauthentication.presentation.util.Screen.ProfileScreen

@Composable
@InternalCoroutinesApi
fun AuthScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        if(authViewModel.isUserAuthenticated) {
            navController.navigate(ProfileScreen.route)
        }
    }

    if (!authViewModel.isUserAuthenticated) {
        Scaffold(
            topBar = {
                AuthTopBar()
            }
        ) {
            AuthContent()

            when(val response = authViewModel.signInState.value) {
                is Loading -> ProgressBar()
                is Success -> {
                    if (response.data) {
                        navController.navigate(ProfileScreen.route)
                    }
                }
                is Error -> Log.d(TAG, response.message)
            }
        }
    }
}