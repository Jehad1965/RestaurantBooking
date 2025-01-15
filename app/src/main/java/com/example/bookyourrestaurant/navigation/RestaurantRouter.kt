package com.example.bookyourrestaurant.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class  Screen(){
    data object SignUpScreen : Screen()
    data object  TermsAndConditionsScreen : Screen()
    data object   LoginScreen : Screen()
    data object   Home :Screen()
    data object   SplashScreen : Screen()
    data object   UserProfile :Screen()
    data object   Restaurant : Screen()
    data object ForgotPasswordScreen : Screen()


}
object RestaurantRouter{
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Home)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}