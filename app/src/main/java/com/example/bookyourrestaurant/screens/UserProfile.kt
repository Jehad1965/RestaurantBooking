package com.example.bookyourrestaurant.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserProfile(){
    val notification = rememberSaveable{ mutableStateOf("") }
    if (notification.value.isNotEmpty()){
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
        notification.value = ""
    }
Column (modifier = Modifier.verticalScroll(rememberScrollState())
    .padding(8.dp))
{
Row (
    modifier = Modifier.fillMaxWidth()
        .padding(8.dp)
){
    Text(
        text = "Cancel",
        modifier = Modifier.clickable { notification.value= "Cancelled" })
    Text(text = "Save",
        modifier = Modifier.clickable { notification.value = "Profile updated" })

}



}

}
@Preview
@Composable
fun UserProfilePreview(){
    UserProfile()
}