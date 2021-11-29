package ro.alexmamo.firebaseanonymousauthentication.domain.use_case

import ro.alexmamo.firebaseanonymousauthentication.domain.repository.AuthRepository

class IsUserAuthenticated(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.isUserAuthenticatedInFirebase()
}