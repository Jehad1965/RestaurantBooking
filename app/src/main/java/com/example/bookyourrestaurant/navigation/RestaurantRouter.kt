package com.example.bookyourrestaurant.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class  Screen(){
    data object SignUpScreen : Screen()
    data object  TermsAndConditionsScreen : Screen()
    data object LoginScreen : Screen()
    data object  HomeScreen :Screen()
    data object SplashScreen : Screen()
}
object RestaurantRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SplashScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}