package com.example.bookyourrestaurant

import android.app.Application
import com.google.firebase.FirebaseApp

class RestaurantFlowApp : Application (){

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }




}