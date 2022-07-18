package ro.alexmamo.firebaseanonymousauthentication.navigation

import ro.alexmamo.firebaseanonymousauthentication.core.Constants.AUTH_SCREEN
import ro.alexmamo.firebaseanonymousauthentication.core.Constants.PROFILE_SCREEN

sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
}