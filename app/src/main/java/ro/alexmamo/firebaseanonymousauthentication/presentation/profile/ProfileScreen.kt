package ro.alexmamo.firebaseanonymousauthentication.presentation.profile

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.alexmamo.firebaseanonymousauthentication.core.Constants.TAG
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.*
import ro.alexmamo.firebaseanonymousauthentication.presentation.profile.components.ProfileContent
import ro.alexmamo.firebaseanonymousauthentication.presentation.profile.components.ProfileTopBar
import ro.alexmamo.firebaseanonymousauthentication.presentation.util.Screen.AuthScreen

@Composable
@InternalCoroutinesApi
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ProfileTopBar()
        }
    ) {
        ProfileContent()

        profileViewModel.getAuthState()
        when(val response = profileViewModel.isUserSignedOutState.value) {
            is Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            is Success -> {
                if (response.data) {
                    navController.popBackStack()
                    navController.navigate(AuthScreen.route)
                }
            }
            is Error -> Log.d(TAG, response.message)
        }
    }
}