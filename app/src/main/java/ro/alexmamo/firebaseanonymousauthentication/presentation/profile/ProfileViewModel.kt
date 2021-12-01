package ro.alexmamo.firebaseanonymousauthentication.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ro.alexmamo.firebaseanonymousauthentication.domain.model.Response
import ro.alexmamo.firebaseanonymousauthentication.domain.use_case.UseCases
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _isUserSignedOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val isUserSignedOutState: State<Response<Boolean>> = _isUserSignedOutState

    fun signOut() {
        viewModelScope.launch {
            useCases.signOut().collect { response ->
                _isUserSignedOutState.value = response
            }
        }
    }

    fun getAuthState() = liveData(Dispatchers.IO) {
        useCases.getAuthState().collect { response ->
            emit(response)
        }
    }
}