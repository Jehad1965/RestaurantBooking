package com.example.bookyourrestaurant.data.signup

  sealed class SignupUIEvent {
      data class FirstNameChanged(val firstName : String): SignupUIEvent()
      data class LastnameChanged(val   lastName : String): SignupUIEvent()
      data class EmailChanged(val email: String): SignupUIEvent()
      data class PasswordChanged(val password: String): SignupUIEvent()

      data class  PrivacyButtonCheckBoxClicked(val status : Boolean) : SignupUIEvent()

       data object RegisterButton : SignupUIEvent()



  }