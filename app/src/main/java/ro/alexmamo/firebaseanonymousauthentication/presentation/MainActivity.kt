package ro.alexmamo.firebaseanonymousauthentication.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.AuthViewModel
import ro.alexmamo.firebaseanonymousauthentication.presentation.navigation.NavGraph
import ro.alexmamo.firebaseanonymousauthentication.presentation.navigation.Screen.ProfileScreen

@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAuthState()
        setContent {
            val navController = rememberAnimatedNavController()
            NavGraph(
                navController = navController
            )
            if(viewModel.isUserAuthenticated) {
                navController.navigate(ProfileScreen.route)
            }
        }
    }
}