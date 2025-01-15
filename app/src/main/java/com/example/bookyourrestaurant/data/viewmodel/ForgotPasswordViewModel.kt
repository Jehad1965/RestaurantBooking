

package com.example.bookyourrestaurant.data.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.bookyourrestaurant.data.login.LoginUIState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow

class ForgotPasswordViewModel : ViewModel() {
    val forgotPasswordUIState = MutableStateFlow(LoginUIState())

    fun onEvent(event: ForgotPasswordUIEvent) {
        when (event) {
            is ForgotPasswordUIEvent.SendResetEmail -> {
                sendPasswordResetEmail(event.email)
            }
        }
    }

    private fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch {
             FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                     if (task.isSuccessful) {
                         Log.d("ForgotPassword", "Password reset email sent.")
                     } else {
                         Log.e("ForgotPassword", "Error sending password reset email.", task.exception)
                    }
                 }
        }
    }
}

sealed class ForgotPasswordUIEvent {
    data class SendResetEmail(val email: String) : ForgotPasswordUIEvent()
}


