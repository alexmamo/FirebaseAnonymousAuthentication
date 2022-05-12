package ro.alexmamo.firebaseanonymousauthentication.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ro.alexmamo.firebaseanonymousauthentication.core.Utils.Companion.printError
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.*
import ro.alexmamo.firebaseanonymousauthentication.presentation.components.ProgressBar
import ro.alexmamo.firebaseanonymousauthentication.presentation.navigation.Screen.AuthScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.profile.components.ProfileContent
import ro.alexmamo.firebaseanonymousauthentication.presentation.profile.components.ProfileTopBar

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ProfileTopBar()
        },
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                ProfileContent()
            }
        }
    )

    when(val response = viewModel.signOutState.value) {
        is Loading -> ProgressBar()
        is Success -> if (response.data) {
            LaunchedEffect(response.data) {
                navController.popBackStack()
                navController.navigate(AuthScreen.route)
            }
        }
        is Error -> LaunchedEffect(Unit) {
            printError(response.message)
        }
    }
}