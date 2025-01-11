
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.navigation.SystemBackButtonHandler

@Composable
fun UserProfile() {
    Image(painter = painterResource(id = R.drawable.back),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize())

    var name by rememberSaveable { mutableStateOf("") }
   var username by rememberSaveable { mutableStateOf("") }
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
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                colors = TextFieldDefaults.colors(Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                colors = TextFieldDefaults.colors(Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
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
        RestaurantRouter.navigateTo(Screen.HomeScreen)

    }
}


@Preview
@Composable
fun UserProfilePreview() {
    UserProfile()
}









/*
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.bookyourrestaurant.R

@Composable
fun UserProfile() {
    var name by rememberSaveable { mutableStateOf("default name") }
    var username by rememberSaveable { mutableStateOf("default name") }
    val notification = rememberSaveable { mutableStateOf("") }
    if (notification.value.isNotEmpty()) {
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
        notification.value = ""
    }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(60.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    text = "Cancel",
                    modifier = Modifier.clickable { notification.value = "Cancelled" })
                Text(fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    text = "Save",
                    modifier = Modifier.clickable { notification.value = "Profile updated" })
            }
        }


    ProfileImage()


    Row(

             modifier = Modifier
                 .height(750.dp)
            .padding(start = 8.dp, end = 2.dp),
             verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = "Name", modifier = Modifier.width(60.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            colors = TextFieldDefaults.colors(Color.Black)


        )


        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically


        ) {

            TextField(
                value = username,
                onValueChange = { username = it },
                colors = TextFieldDefaults.colors(Color.Transparent)


            )
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
                .padding(70.dp)
                .size(200.dp)
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
}


@Preview
@Composable
fun UserProfilePreview() {
    UserProfile()
}
*/