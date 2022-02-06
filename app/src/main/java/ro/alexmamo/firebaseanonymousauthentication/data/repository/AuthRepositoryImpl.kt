package ro.alexmamo.firebaseanonymousauthentication.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.*
import ro.alexmamo.firebaseanonymousauthentication.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class AuthRepositoryImpl  @Inject constructor(
    private val auth: FirebaseAuth
): AuthRepository {
    override fun isUserAuthenticatedInFirebase() = auth.currentUser != null

    override suspend fun firebaseSignInAnonymously() = flow {
        try {
            emit(Loading)
            auth.signInAnonymously().await()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override suspend fun signOut() = flow {
        try {
            emit(Loading)
            auth.currentUser?.apply {
                delete().await()
                emit(Success(true))
            }
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override fun getFirebaseAuthState() = callbackFlow  {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }
}