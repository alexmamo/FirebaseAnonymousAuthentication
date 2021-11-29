package ro.alexmamo.firebaseanonymousauthentication.domain.use_case

data class UseCases (
    val isUserAuthenticated: IsUserAuthenticated,
    val singInAnonymously: SingInAnonymously,
    val singOut: SignOut,
    val getAuthState: GetAuthState
)