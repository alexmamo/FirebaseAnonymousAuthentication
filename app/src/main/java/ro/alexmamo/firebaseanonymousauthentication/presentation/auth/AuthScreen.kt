package ro.alexmamo.firebaseanonymousauthentication.presentation.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firebaseanonymousauthentication.core.Utils.Companion.printError
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.*
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthContent
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components.AuthTopBar
import ro.alexmamo.firebaseanonymousauthentication.components.ProgressBar

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
            Box(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                AuthContent(
                    signIn = {
                        viewModel.signIn()
                    }
                )
            }
        }
    )

    when(val response = viewModel.signInState.value) {
        is Loading -> ProgressBar()
        is Success -> if (response.data) {
            LaunchedEffect(response.data) {
                navigateToProfileScreen()
            }
        }
        is Error -> LaunchedEffect(Unit) {
            printError(response.message)
        }
    }
}