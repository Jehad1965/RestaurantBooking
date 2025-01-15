package com.example.bookyourrestaurant.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookyourrestaurant.data.RegistrationUIState
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class SignupViewModel : ViewModel() {

    private val tAG = SignupViewModel :: class.simpleName
    var registrationUIState = mutableStateOf(RegistrationUIState())
    var allValidationPassed = mutableStateOf(false)
    var signUpProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent) {
        validateDataWithRules()
        when(event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                validateDataWithRules()
                printState()
            }

            is SignupUIEvent.LastnameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName

                )
               validateDataWithRules()
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email

                )
               validateDataWithRules()
                printState()

            }

            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password

                )
               validateDataWithRules()
                printState()

            }
             is SignupUIEvent.RegisterButton -> {
                 signUp()
             }
            is SignupUIEvent.PrivacyButtonCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                privacyPolicyAccepted = event.status

                )

            }

        }
    }

    private fun signUp() {
        Log.d(tAG, "Inside_signUp" )
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )

    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName

        )
        val lNameResult = Validator.validateLastName(
             lName  = registrationUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email =  registrationUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password =  registrationUIState.value.password
        )
        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(

            statusValue = registrationUIState.value.privacyPolicyAccepted

        )
        Log.d(tAG, "Inside_validateDataWithRules" )
        Log.d(tAG, "fNameResult = $fNameResult" )
        Log.d(tAG, "lNameResult = $lNameResult" )
        Log.d(tAG, "emailResult= $emailResult" )
        Log.d(tAG, "passwordResult = $passwordResult" )
        Log.d(tAG, "privacyPolicyResult = $privacyPolicyResult " )

        registrationUIState.value= registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status

        )
         allValidationPassed.value = fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status && privacyPolicyResult.status

    }
    private fun printState(){
        Log.d(tAG, "Inside_printState" )
        Log.d(tAG, registrationUIState.value.toString())
    }
   private fun createUserInFirebase(email : String, password: String){
       signUpProgress.value = true
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    Log.d(tAG, "Inside_OnCompleteListener")
                    Log.d(tAG, "  isSuccessful = ${it.isSuccessful}")

                    if(it.isSuccessful) {
                        signUpProgress.value=false
                        RestaurantRouter.navigateTo(Screen.Home)
                    }
                }
                .addOnFailureListener{
                    Log.d(tAG, "Inside_OnFailureListener")
                    Log.d(tAG,"Exception = ${it.localizedMessage}")
                }

    }


}