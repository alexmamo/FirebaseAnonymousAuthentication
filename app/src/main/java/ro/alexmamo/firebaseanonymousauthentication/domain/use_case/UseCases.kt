package ro.alexmamo.firebaseanonymousauthentication.domain.use_case

data class UseCases (
    val isUserAuthenticated: IsUserAuthenticated,
    val signInAnonymously: SignInAnonymously,
    val signOut: SignOut,
    val getAuthState: GetAuthState
)