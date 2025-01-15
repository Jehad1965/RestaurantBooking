package com.example.bookyourrestaurant.screens
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.componants.AppToolBar
import com.example.bookyourrestaurant.componants.NavigationDrawerBody
import com.example.bookyourrestaurant.componants.NavigationDrawerHeader
import com.example.bookyourrestaurant.data.home.HomeViewModel
import com.example.bookyourrestaurant.navigation.RestaurantRouter
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {

            Column (modifier = Modifier
                .width(300.dp)
                .fillMaxSize()
                .background(Color(0xffdaa520))
                .padding(2.dp)){
                NavigationDrawerHeader()
                Column (modifier = Modifier.height(70.dp)
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFF7F8F8))) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Cursive,
                        text = "Welcome back",
                        modifier = Modifier.padding(8.dp)
                    )
                }

                NavigationDrawerBody(
                    navigationDrawerItems = homeViewModel.navigationItemList,
                    onNavigationItemClicked = { screen ->
                        coroutineScope.launch {
                            Log.d("Navigation", "Navigating to $screen")
                            RestaurantRouter.navigateTo(screen)
                            drawerState.close()
                        }
                    }
                )
            }
        }
    ) {

        Scaffold(
            topBar = {
                AppToolBar(
                    toolbarTitle = stringResource(id = R.string.home),
                    logoutButtonClicked = {
                        homeViewModel.logout()
                    },
                    navigationIconClicked = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                )
            },
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                    color = Color.LightGray
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                            .padding(2.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenReview() {
    HomeScreen()
}






