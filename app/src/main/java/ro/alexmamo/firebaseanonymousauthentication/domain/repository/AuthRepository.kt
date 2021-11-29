package ro.alexmamo.firebaseanonymousauthentication.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response

interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean

    suspend fun firebaseSignInAnonymously(): Flow<Response<Boolean>>

    suspend fun signOut(): Flow<Response<Boolean>>

    fun getFirebaseAuthState(): Flow<Boolean>
}