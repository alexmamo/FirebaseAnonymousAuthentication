package ro.alexmamo.firebaseanonymousauthentication.domain.use_case

import ro.alexmamo.firebaseanonymousauthentication.domain.repository.AuthRepository

class SingInAnonymously(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.firebaseSignInAnonymously()
}