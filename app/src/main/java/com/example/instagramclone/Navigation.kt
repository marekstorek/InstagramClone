package com.example.instagramclone

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.instagramclone.screens.HomeScreen
import com.example.instagramclone.screens.MyProfileScreen
import com.example.instagramclone.screens.ReelsScreen
import com.example.instagramclone.screens.Screen
import com.example.instagramclone.screens.SearchScreen

@Composable
fun Navigation(navController: NavHostController, pv: PaddingValues, pagerState: PagerState){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.fillMaxSize().padding(pv)
    ){
        composable(route = Screen.Home.route) {
            HomeScreen(Modifier,pagerState,navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen()
        }
        composable(route = Screen.MyProfile.route) {
            MyProfileScreen()
        }
        composable(route = Screen.Reels.route) {
            ReelsScreen()
        }
    }
}