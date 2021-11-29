package ro.alexmamo.firebaseanonymousauthentication.presentation.auth

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.alexmamo.firebaseanonymousauthentication.core.Constants.TAG
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.*
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthContent
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthTopBar
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
                is Loading -> Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
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