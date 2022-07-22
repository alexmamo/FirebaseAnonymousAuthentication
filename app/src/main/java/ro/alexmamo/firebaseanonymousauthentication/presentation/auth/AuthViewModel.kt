package ro.alexmamo.firebaseanonymousauthentication.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.Success
import ro.alexmamo.firebaseanonymousauthentication.domain.use_case.UseCases
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    val isUserAuthenticated get() = useCases.isUserAuthenticated()
    var signInResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set

    fun getAuthState() = liveData(Dispatchers.IO) {
        useCases.getAuthState().collect { response ->
            emit(response)
        }
    }

    fun signIn() = viewModelScope.launch {
        useCases.signInAnonymously().collect { response ->
            signInResponse = response
        }
    }
}