package com.example.bookyourrestaurant.data.rules

object Validator {


    fun validateFirstName(fName: String) : ValidationResult {
      return ValidationResult(
          (fName.isNotEmpty() && fName.length >= 5)

      )
    }

    fun validateLastName(lName: String) : ValidationResult {

        return ValidationResult(
            (lName.isNotEmpty() && lName.length >= 5))

    }
    fun validateEmail(email: String) : ValidationResult {
        return ValidationResult(
            (email.isNotEmpty())
        )
    }
    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (password.isNotEmpty() && password.length >= 6))

    }
    fun validatePrivacyPolicyAcceptance(statusValue : Boolean): ValidationResult {
        return  ValidationResult(statusValue)

    }
}
data class ValidationResult(val status : Boolean = false)

