package com.example.bookyourrestaurant.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel(){

    private val tAG = LoginViewModel :: class.simpleName
    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationPassed = mutableStateOf(false)
    var logInProgress = mutableStateOf(false)

    fun onEvent(event : LoginUIEvent){
        when(event){
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email

                )

            }
            is LoginUIEvent.PasswordChanged ->{
                loginUIState.value = loginUIState.value.copy(
                    password = event.password

                )


            }
            is LoginUIEvent.LoginButton ->{
             login()

            }
            is LoginUIEvent.ForgotPassword ->{
                Screen.ForgotPasswordScreen
            }
        }
        validateLoginUIDataWithRules()


    }


     private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email =  loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password =  loginUIState.value.password
        )

            loginUIState.value= loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status


        )
        allValidationPassed.value =  emailResult.status && passwordResult.status

    }

    private fun login() {
        logInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password
     FirebaseAuth.getInstance()
         .signInWithEmailAndPassword(email, password)
         .addOnCompleteListener {

               Log.d(tAG,"Inside_login_success")
               Log.d(tAG, "${it.isSuccessful}")


             if(it.isSuccessful){
                 logInProgress.value= false
                 RestaurantRouter.navigateTo(Screen.Home)
             }


         }
         .addOnFailureListener{
            Log.d(tAG, "Inside_login_failure")
             it.localizedMessage?.let { it1 -> Log.d(tAG, it1) }
         }
    }

}