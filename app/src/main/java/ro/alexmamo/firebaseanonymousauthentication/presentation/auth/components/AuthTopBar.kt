package ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import ro.alexmamo.firebaseanonymousauthentication.core.Constants.AUTH_SCREEN

@Composable
fun AuthTopBar() {
    TopAppBar (
        title = {
            Text(
                text = AUTH_SCREEN
            )
        }
    )
}