
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.componants.MyTextFieldComponent
import com.example.bookyourrestaurant.data.viewmodel.ForgotPasswordUIEvent
import com.example.bookyourrestaurant.data.viewmodel.ForgotPasswordViewModel
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.navigation.SystemBackButtonHandler


@Composable
fun ForgotPasswordScreen(forgotPasswordViewModel: ForgotPasswordViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        //  Image at the top of  thee screen
        Image(
            painter = painterResource(id = R.drawable.restaurant),
            contentDescription = stringResource(id = R.string.welcome),
            modifier = Modifier
                .size(300.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.forgot_password))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.email),
                onTextSelected = {
                    email = it
                },

                )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    forgotPasswordViewModel.onEvent(ForgotPasswordUIEvent.SendResetEmail(email))
                }
            ) {
                Text(text = stringResource(id = R.string.reset_password))
            }
            SystemBackButtonHandler {
                RestaurantRouter.navigateTo(Screen.LoginScreen)
            }
        }
    }
}
@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen()
}





/*import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.componants.MyTextFieldComponent
import com.example.bookyourrestaurant.data.login.LoginUIEvent
import com.example.bookyourrestaurant.data.login.LoginViewModel
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.navigation.SystemBackButtonHandler

@Composable
fun ForgotPasswordScreen(loginViewModel: LoginViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        //  Image at the top of  thee screen
        Image(
            painter = painterResource(id = R.drawable.restaurant),
            contentDescription = stringResource(id = R.string.welcome),
            modifier = Modifier
                .size(300.dp)

        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.forgot_password))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.email),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.emailError
            )


            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    loginViewModel.onEvent(LoginUIEvent.ForgotPassword(email))
                }


            ) {
                Text(text = stringResource(id = R.string.reset_password))
            }
        }
        SystemBackButtonHandler {
            RestaurantRouter.navigateTo(Screen.LoginScreen)
        }
    }
}

@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen()
}*/


/*
package com.example.bookyourrestaurant.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.componants.MyTextFieldComponent
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.navigation.SystemBackButtonHandler
import com.example.bookyourrestaurant.data.viewmodel.ForgotPasswordUIEvent
import com.example.bookyourrestaurant.data.viewmodel.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(forgotPasswordViewModel: ForgotPasswordViewModel = viewModel() ) {
    var email by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        // Add Image at the top
        Image(
            painter = painterResource(id = R.drawable.restaurant),
            contentDescription = stringResource(id = R.string.welcome),
            modifier = Modifier
                .size(300.dp)

        )
          Spacer(modifier = Modifier.height(20.dp))
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.forgot_password))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.email),
                onTextSelected = {
                    email = it
                },

            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    forgotPasswordViewModel.onEvent(ForgotPasswordUIEvent.SendResetEmail(email))
                }
            ) {
                Text(text = stringResource(id = R.string.reset_password))
            }

              }

            }
        SystemBackButtonHandler {
            RestaurantRouter.navigateTo(Screen.LoginScreen)
        }
    }


@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen()
}

*/
