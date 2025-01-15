package com.example.bookyourrestaurant.data

import androidx.annotation.DrawableRes
import com.example.bookyourrestaurant.R
import com.google.android.gms.maps.model.LatLng

data class Restaurant(
    val id: Int,
    val name: String,
    val address: String,
    val imageResId: Int,
    val location: LatLng // Latitude and Longitude coordinates
)

fun getRestaurants(): List<Restaurant> {
    return listOf(
        Restaurant(
            id = 1,
            name = " Nova Restaurant ",
            address = "Wheatly Hill , Durham DH6 3RH,Phone:  01429 821036",
            imageResId = R.drawable.pizanova,
            location = LatLng(54.7762, -1.5733) // Coordinates Durham
        ),
        Restaurant(
            id = 2,
            name = "Peri Peri's Restaurant",
            address = "192-194, Linthorp Rd,Middlesbrough  TS1 3RF, phone: 01642 512244",
            imageResId = R.drawable.peri,
            location = LatLng(53.4808, -2.2426) // Coordinates for Middlesbrough
        ),
        Restaurant(
            id = 3,
            name = "Akbar's Restaurant",
            address = "184, Linthorp Rd, Middlesbrough,  TS1 3RF,  phone: 01642 244566",
            imageResId = R.drawable.akbar,
            location = LatLng(54.5762, -1.2348) // Coordinates for Middlesbrough
        ),
                Restaurant(
                id = 3,
        name = "King Road Parmesan House",
        address = "60-64 , Kings Rd,  North Ormesby, Middlesbrough TS36EP , phone: 01642 226969 ",
        imageResId = R.drawable.pamesahouse,
        location = LatLng(54.5762, -1.2348) // Coordinates for Middlesbrough
    )
        // Add more restaurants as needed
    )
}




  /*  Restaurant(
        1,
        "Nova Restaurant",
        "Peter LeeCottage,  Wheatly Hill , Durham DH6 3RH,Phone:  01429 821036",
        "Wheatly Hill , Durham DH6 3RH", R.drawable.pizanova),
    Restaurant( 2, "King Road Parmesan House", "60-64 , Kings Rd,  North Ormesby, Middlesbrough TS36EP , phone: 01642 226969 ","Middlesbrough TS36EP",R.drawable.pamesahouse ),
    Restaurant(3,"Peri Peri's Restaurant",   "192-194, Linthorp Rd,Middlesbrough  TS1 3RF, phone: 01642 512244","Middlesbrough  TS1 3RF", R.drawable.peri) ,
    Restaurant(4,"Akbar's Restaurant",       "184, Linthorp Rd, Middlesbrough,  TS1 3RF,  phone: 01642 244566","Middlesbrough,  TS1 3RF", R.drawable.akbar))


*/




