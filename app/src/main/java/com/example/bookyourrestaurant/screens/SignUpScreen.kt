package com.example.bookyourrestaurant.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.componants.*
import com.example.bookyourrestaurant.data.signup.SignupViewModel
import com.example.bookyourrestaurant.data.signup.SignupUIEvent
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen

@Composable
fun SignUpScreen(signupViewModel: SignupViewModel = viewModel()) {

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center)
    {
        Image(painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize())


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Add Image at the top
                Image(
                    painter = painterResource(id = R.drawable.restaurant),
                    contentDescription = stringResource(id = R.string.welcome),
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 20.dp)
                )

                NormalTextComponent(value = stringResource(id = R.string.hello))
                NormalTextComponent(value = stringResource(id = R.string.create_account))

                Spacer(Modifier.height(20.dp))

                MyTextFieldComponent(

                    labelValue = stringResource(id = R.string.firstname),
                    painterResource(id = R.drawable.user),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.firstNameError

                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.lastname),
                    painterResource(id = R.drawable.user),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.LastnameChanged(it))
                    },
                    errorStatus =signupViewModel.registrationUIState.value.lastNameError
                )
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.message),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )
                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus =signupViewModel.registrationUIState.value.passwordError
                )

                TestBoxComponent(
                    value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = {
                        RestaurantRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    },
                    onCheckChange = {
                        signupViewModel.onEvent(SignupUIEvent.PrivacyButtonCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(value = stringResource(id = R.string.register),

                    onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButton)
                    },
                    isEnabled = signupViewModel.allValidationPassed.value)
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    // Handle login click
                    RestaurantRouter.navigateTo(Screen.LoginScreen) })
            }
        }
        if (signupViewModel.signUpProgress.value) {
            CircularProgressIndicator()
    }


    }




@Preview
@Composable
fun ReviewOfSignUpScreen() {
    SignUpScreen()
}


