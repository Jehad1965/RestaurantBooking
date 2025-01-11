package com.example.bookyourrestaurant.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.example.bookyourrestaurant.screens.HomeScreen
import com.example.bookyourrestaurant.screens.LoginScreen
import com.example.bookyourrestaurant.screens.SignUpScreen
import com.example.bookyourrestaurant.screens.TermsAndConditionsScreen
import com.example.bookyourrestaurant.screens.UserProfile
import com.example.bookyourrestaurant.screens.splashScreen


@Composable
fun RestaurantApp(){
    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Crossfade(targetState = RestaurantRouter.currentScreen, label = "") { currentState->
            when(currentState.value){
               is Screen.SignUpScreen -> {
                    SignUpScreen()
                }
              is Screen.TermsAndConditionsScreen ->{
                  TermsAndConditionsScreen()
              }
                is Screen.LoginScreen ->{
                    LoginScreen()


                }
                is Screen.HomeScreen ->{
                    HomeScreen()
                }

                Screen.SplashScreen -> {
                    splashScreen()
                }
                Screen.UserProfile -> {
                    UserProfile()
                }
            }


        }


    }
}