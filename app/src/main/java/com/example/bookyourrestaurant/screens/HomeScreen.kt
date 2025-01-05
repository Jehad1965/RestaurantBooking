package com.example.bookyourrestaurant.screens


import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookyourrestaurant.R
import com.example.bookyourrestaurant.componants.AppToolBar
import com.example.bookyourrestaurant.componants.NavigationDrawerBody
import com.example.bookyourrestaurant.componants.NavigationDrawerHeader
import com.example.bookyourrestaurant.data.home.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            NavigationDrawerHeader()
            NavigationDrawerBody(
                navigationDrawerItems = homeViewModel.navigationItemList,
                onNavigationItemClicked = {
                    Log.d("ComingHere", "inside_onNavigationItemClicked")
                    Log.d("ComingHere", "${it.itemId} ${it.title}")
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        Spacer(modifier = Modifier.height(20.dp))

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
                // Your screen content goes here
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenReview() {
    HomeScreen()
}

