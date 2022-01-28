package ro.alexmamo.firebaseanonymousauthentication.presentation.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.alexmamo.firebaseanonymousauthentication.core.Constants.WELCOME_MESSAGE

@Composable
fun ProfileContent() {
    Box(
        modifier = Modifier.fillMaxSize().padding(top = 48.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = WELCOME_MESSAGE,
            fontSize = 24.sp
        )
    }
}