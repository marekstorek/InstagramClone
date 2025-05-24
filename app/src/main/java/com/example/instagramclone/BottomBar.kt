package com.example.instagramclone

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.instagramclone.data.Dummy
import com.example.instagramclone.screens.Screen
import com.example.instagramclone.screens.bottomNavScreens
import kotlinx.coroutines.launch

@Composable
fun BottomBar(navController: NavHostController, pagerState: PagerState){
    Column(Modifier.background(MaterialTheme.colorScheme.background)){
        HorizontalDivider(
            thickness = 0.5.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(vertical = 0.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){

            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val route = currentBackStackEntry?.destination?.route
            val scope = rememberCoroutineScope()

            bottomNavScreens.forEach { screen->
                if (screen == Screen.MyProfile) {
                    Box(
                        modifier = Modifier.weight(1f).clickableNoEffect {
                            navController.navigate(screen.route)
                        },
                        contentAlignment = Alignment.Center
                    ){
                        ProfilePhoto(0,if (route == screen.route) 38.dp else 33.dp, photo = Dummy.myProfile.profilePhoto)
                    }
                } else {
                    var drawable by remember {
                        mutableIntStateOf(R.drawable.ic_error_24)
                    }
                    drawable = if (route == screen.route){
                        screen.selectedIcon ?: (screen.defaultIcon ?: R.drawable.ic_error_24)
                    } else {
                        screen.defaultIcon ?: R.drawable.ic_error_24
                    }
                    Icon(
                        modifier = Modifier.weight(1f).clickableNoEffect {
                            if(screen == Screen.NewPost){
                                scope.launch {
                                    pagerState.animateScrollToPage(0, animationSpec = tween(durationMillis = 800))
                                }
                            } else{
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        },
                        painter = painterResource(drawable),
                        contentDescription = screen.title
                    )
                }

            }
        }
    }
}