
package com.example.bookyourrestaurant.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.componants.MyTextFieldComponent
import com.example.bookyourrestaurant.componants.PasswordTextField
import com.example.bookyourrestaurant.data.login.LoginUIEvent
import com.example.bookyourrestaurant.data.login.LoginViewModel
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.navigation.SystemBackButtonHandler

@Composable
fun UserProfile(loginViewModel: LoginViewModel = viewModel()) {
    Image(painter = painterResource(id = R.drawable.back),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize())
    val notification = rememberSaveable { mutableStateOf("") }
    if (notification.value.isNotEmpty()) {
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
        notification.value = ""
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        ProfileImage()

        Column(modifier = Modifier.fillMaxWidth()) {
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
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { notification.value = "Cancelled" },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "Cancel")
            }
            Button(
                onClick = { notification.value = "Profile updated" },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "Save")
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileImage() {
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberImagePainter(
        if (imageUri.value.isEmpty())
            R.drawable.person_24
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }

    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(10.dp)
                .size(250.dp)
                .fillMaxWidth()

        ) {
            Image(
                painter = painter, contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )

        }

        Text(text = "Change Profile Picture")
    }
    SystemBackButtonHandler {
        RestaurantRouter.navigateTo(Screen.Home)

    }
}


@Preview
@Composable
fun UserProfilePreview() {
    UserProfile()
}









