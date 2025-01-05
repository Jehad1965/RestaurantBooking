package com.example.bookyourrestaurant.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookyourrestaurant.data.login.LoginViewModel
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.componants.ButtonComponent
import com.example.bookyourrestaurant.componants.ClickableLoginTextComponent
import com.example.bookyourrestaurant.componants.DividerTextComponent
import com.example.bookyourrestaurant.componants.MyTextFieldComponent
import com.example.bookyourrestaurant.componants.NormalTextComponent
import com.example.bookyourrestaurant.componants.PasswordTextField
import com.example.bookyourrestaurant.componants.UnderlineTextComponent
import com.example.bookyourrestaurant.data.login.LoginUIEvent
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.navigation.SystemBackButtonHandler

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White
                )
                .padding(28.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
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
                NormalTextComponent(value = stringResource(id = R.string.log))

                Spacer(Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.message),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )
                Spacer(modifier = Modifier.height(40.dp))
                UnderlineTextComponent(value = stringResource(id = R.string.forgot))

                Spacer(Modifier.height(40.dp))
                ButtonComponent(
                    value = stringResource(id = R.string.login),

                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButton)

                    },
                    isEnabled = loginViewModel.allValidationPassed.value
                )
                Spacer(Modifier.height(20.dp))
                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    RestaurantRouter.navigateTo(Screen.SignUpScreen)
                    // Handle login click
                })
            }
        }
    }
    if (loginViewModel.logInProgress.value) {
        CircularProgressIndicator()
    }
    SystemBackButtonHandler {
        RestaurantRouter.navigateTo(Screen.SignUpScreen)
    }
}


@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}