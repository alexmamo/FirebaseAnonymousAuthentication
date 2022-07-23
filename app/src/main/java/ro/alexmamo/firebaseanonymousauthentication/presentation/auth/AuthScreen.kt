package ro.alexmamo.firebaseanonymousauthentication.presentation.auth

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firebaseanonymousauthentication.components.ProgressBar
import ro.alexmamo.firebaseanonymousauthentication.core.Utils.Companion.printMessage
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.*
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthContent
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthTopBar

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            AuthTopBar()
        },
        content = { padding ->
            AuthContent(
                padding = padding,
                signIn = {
                    viewModel.signIn()
                }
            )
        }
    )

    when(val signInResponse = viewModel.signInResponse) {
        is Loading -> ProgressBar()
        is Success -> if (signInResponse.data) {
            LaunchedEffect(signInResponse.data) {
                navigateToProfileScreen()
            }
        }
        is Error -> LaunchedEffect(Unit) {
            printMessage(signInResponse.message)
        }
    }
}