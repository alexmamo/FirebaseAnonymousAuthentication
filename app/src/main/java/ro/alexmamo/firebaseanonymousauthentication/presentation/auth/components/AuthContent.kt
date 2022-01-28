package ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.AuthViewModel
import ro.alexmamo.firebaseanonymousauthentication.core.Constants.SIGN_IN

@Composable
@InternalCoroutinesApi
fun AuthContent(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 48.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = {
                authViewModel.signInAnonymously()
            }
        ) {
            Text(
                text = SIGN_IN,
                fontSize = 18.sp
            )
        }
    }
}