
package com.example.bookyourrestaurant.data.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookyourrestaurant.data.signup.SignupViewModel
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {
    // Initialize the list of navigation items
    val navigationItemList = listOf(
        Screen.Home,
        Screen.UserProfile,
        Screen.Restaurant )



    fun logout() {
        val tAG = SignupViewModel::class.simpleName
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                val tAG = null
                Log.d(tAG, "Inside sign success")
                RestaurantRouter.navigateTo(Screen.LoginScreen)
            } else {

                Log.d(tAG, "Inside sign out is not complete")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
}



/*
package com.example.bookyourrestaurant.data.home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.lifecycle.ViewModel
import com.example.bookyourrestaurant.data.NavigationItem
import com.example.bookyourrestaurant.data.signup.SignupViewModel
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import com.example.bookyourrestaurant.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {
   private  val tAG = SignupViewModel :: class.simpleName
   val navigationItemList = listOf(
       NavigationItem(
           title = "Home",
           icon = Icons.Default.Home,
           description = "HomeScreen",
           itemId = "homeScreen"
       ),
       NavigationItem(
           title = "User profile",
           icon = Icons.Default.AccountCircle,
           description = "User profile Screen",
           itemId = "User profile"
       ),
       NavigationItem(
           title = "Restaurant",
           icon = Icons.Default.Restaurant,
           description = "Restaurant Screen",
           itemId = "Restaurants"
   )
   )
}
}
 */





