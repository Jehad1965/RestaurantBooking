package com.example.bookyourrestaurant.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun splashScreen() {


    val scale = remember {
        Animatable(0f)
    }
    // AnimationEffect

    LaunchedEffect(key1 = true) {

        scale.animateTo(
            targetValue = 2f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(3f).getInterpolation(it)

                })
        )
        delay(3000L)
        RestaurantRouter.navigateTo(Screen.LoginScreen)

    }
    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
            .background(Color(0xffffc107))){

        Image(painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize())

        // change the logo
        Image(painter = painterResource(id = R.drawable.restaurant),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }



}
