package ro.alexmamo.firebaseanonymousauthentication.presentation.auth.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.alexmamo.firebaseanonymousauthentication.core.Constants.SIGN_IN

@Composable
fun AuthContent(
    signIn: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 48.dp),
        contentAlignment = BottomCenter
    ) {
        Button(
            onClick = signIn
        ) {
            Text(
                text = SIGN_IN,
                fontSize = 18.sp
            )
        }
    }
}