package com.example.bookyourrestaurant.data.home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.lifecycle.ViewModel
import com.example.bookyourrestaurant.data.NavigationItem
import com.example.bookyourrestaurant.data.signup.SignupViewModel
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {
   private  val tAG = SignupViewModel :: class.simpleName
   val navigationItemList = listOf<NavigationItem>(
       NavigationItem(
           title = "Home",
           icon = Icons.Default.Home,
           description = "HomeScreen",
           itemId = "homeScreen"
       ),
       NavigationItem(
           title = "Settings",
           icon = Icons.Default.Settings,
           description = "Settings Screen",
           itemId = "SettingsScreen"
       ),
       NavigationItem(
           title = "Favorite",
           icon = Icons.Default.Favorite,
           description = "Favorite Screen",
           itemId = "FavoriteScreen"
   )
   )

    fun logout(){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(tAG, "Inside sign outsuccess")
                RestaurantRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(tAG, "Inside sign out is not complete")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }



}