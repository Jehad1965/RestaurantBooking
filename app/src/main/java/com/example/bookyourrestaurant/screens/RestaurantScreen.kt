package com.example.bookyourrestaurant.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookyourrestaurant.data.Restaurant
import com.example.bookyourrestaurant.data.getRestaurants
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.navigation.SystemBackButtonHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantScreen() {
    var searchText by remember { mutableStateOf("") }
    val restaurants = getRestaurants() // Function to fetch restaurant data


    Scaffold(
        topBar = {

            TopAppBar(
                title = { Text("Book Your Restaurant") }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color(0xffb0c4de)
        ) {
            Column {
                SearchBar(searchText) { newText ->
                    searchText = newText
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(restaurants.filter {
                        it.name.contains(searchText, ignoreCase = true)
                    }) { restaurant ->
                        RestaurantItem(restaurant)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(searchText: String, onSearchTextChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp)
            .background(Color.White, shape = MaterialTheme.shapes.small)
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            singleLine = true,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
            decorationBox = { innerTextField ->
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (searchText.isEmpty()) {
                        Text(
                            text = "Search restaurants",
                            style = TextStyle(color = Color.Gray, fontSize = 18.sp)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun RestaurantItem(restaurant: Restaurant) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xfff5f5dc))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = restaurant.imageResId),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(restaurant.name, style = MaterialTheme.typography.titleMedium)
        Text(restaurant.address, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Book restaurant logic */ }) {
            Text("Book Now")
        }
        SystemBackButtonHandler {
            RestaurantRouter.navigateTo(Screen.Home)
        }
    }
}

@Preview
@Composable
fun RestaurantScreenPreview() {
    RestaurantScreen()
}




