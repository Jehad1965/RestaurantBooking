package com.example.bookyourrestaurant.navigation

import android.graphics.pdf.content.PdfPageGotoLinkContent.Destination
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class  Screen(){
    data object SignUpScreen : Screen()
    data object  TermsAndConditionsScreen : Screen()
    data object LoginScreen : Screen()
    object  HomeScreen :Screen()
    object SplashScreen : Screen()
}
object RestaurantRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.HomeScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}