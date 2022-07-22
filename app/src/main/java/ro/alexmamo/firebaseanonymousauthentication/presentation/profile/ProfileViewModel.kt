package ro.alexmamo.firebaseanonymousauthentication.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response.Success
import ro.alexmamo.firebaseanonymousauthentication.domain.use_case.UseCases
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    var signOutResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set

    fun signOut() = viewModelScope.launch {
        useCases.signOut().collect { response ->
            signOutResponse = response
        }
    }
}