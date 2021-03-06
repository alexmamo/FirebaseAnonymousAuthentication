package ro.alexmamo.firebaseanonymousauthentication.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import ro.alexmamo.firebaseanonymousauthentication.navigation.NavGraph
import ro.alexmamo.firebaseanonymousauthentication.navigation.Screen.ProfileScreen
import ro.alexmamo.firebaseanonymousauthentication.presentation.auth.AuthViewModel

@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberAnimatedNavController()
            NavGraph(
                navController = navController
            )
            checkAuthStatus()
            getAuthState()
        }
    }

    private fun checkAuthStatus() {
        if(viewModel.isUserAuthenticated) {
            navController.navigate(ProfileScreen.route)
        }
    }

    private fun getAuthState() = viewModel.getAuthState()
}