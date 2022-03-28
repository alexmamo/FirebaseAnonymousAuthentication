package ro.alexmamo.firebaseanonymousauthentication.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ro.alexmamo.firebaseanonymousauthentication.data.repository.AuthRepositoryImpl
import ro.alexmamo.firebaseanonymousauthentication.domain.repository.AuthRepository
import ro.alexmamo.firebaseanonymousauthentication.domain.use_case.*

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository = AuthRepositoryImpl(auth)

    @Provides
    fun provideUseCases(repository: AuthRepository) = UseCases(
        isUserAuthenticated = IsUserAuthenticated(repository),
        signInAnonymously = SignInAnonymously(repository),
        signOut = SignOut(repository),
        getAuthState = GetAuthState(repository)
    )
}