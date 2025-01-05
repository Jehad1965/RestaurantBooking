
package com.example.bookyourrestaurant.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
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
        drawerContent = {
            NavigationDrawerHeader()
            NavigationDrawerBody(navigationDrawerItems = homeViewModel.navigationItemList)
        }

    )
    {
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
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = Color.Yellow
            ) {

            }
        }
    }
}

@Preview
@Composable
fun HomeScreenReview() {
    HomeScreen()
}

