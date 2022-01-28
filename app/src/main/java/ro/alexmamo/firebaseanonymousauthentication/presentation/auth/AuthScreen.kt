package ro.alexmamo.firebaseanonymousauthentication.presentation.auth

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.alexmamo.firebaseanonymousauthentication.core.Utils.Companion.printError
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.*
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthContent
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthTopBar
import ro.alexmamo.firebaseanonymousauthentication.presentation.components.ProgressBar
import ro.alexmamo.firebaseanonymousauthentication.presentation.navigation.Screen.ProfileScreen

@Composable
@InternalCoroutinesApi
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            AuthTopBar()
        }
    ) {
        AuthContent()
    }

    when(val response = viewModel.signInState.value) {
        is Loading -> ProgressBar()
        is Success -> if (response.data) {
            navController.navigate(ProfileScreen.route)
        }
        is Error -> printError(response.message)
    }
}