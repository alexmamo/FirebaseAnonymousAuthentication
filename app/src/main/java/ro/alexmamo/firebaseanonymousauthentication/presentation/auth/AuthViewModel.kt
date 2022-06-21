package ro.alexmamo.firebaseanonymousauthentication.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _signInState = mutableStateOf<Response<Boolean>>(Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    fun signIn() = viewModelScope.launch {
        useCases.signInAnonymously().collect { response ->
            _signInState.value = response
        }
    }

    fun getAuthState() = liveData(Dispatchers.IO) {
        useCases.getAuthState().collect { response ->
            emit(response)
        }
    }
}