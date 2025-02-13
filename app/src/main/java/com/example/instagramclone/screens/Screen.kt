package com.example.instagramclone.screens

import androidx.annotation.DrawableRes
import com.example.instagramclone.R

sealed class Screen (
    val title: String,
    val route: String,
    @DrawableRes val defaultIcon: Int? = null,
    @DrawableRes val selectedIcon: Int? = null
) {

    data object Home : Screen("Home", "home", R.drawable.ic_home_24, R.drawable.ic_home_filled_24)

    data object Search : Screen("Search", "search", R.drawable.ic_search_24, R.drawable.ic_search_filled_24)

    data object NewPost  : Screen("New Post", "new_post", R.drawable.ic_new_post_24)

    data object Reels  : Screen("Reels", "reels", R.drawable.ic_reels_24, R.drawable.ic_reels_filled_24)

    data object MyProfile  : Screen("My Profile", "my_profile")
}

val bottomNavScreens = arrayOf(Screen.Home, Screen.Search, Screen.NewPost, Screen.Reels, Screen.MyProfile,
)