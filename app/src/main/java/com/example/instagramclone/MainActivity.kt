package com.example.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.screens.ChatsScreen
import com.example.instagramclone.screens.NewPostScreen
import com.example.instagramclone.screens.Screen
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var condition = true
        installSplashScreen().apply {
            this.setKeepOnScreenCondition{condition}
            lifecycleScope.launch {
                delay(2000)
                condition = false
            }
        }

        setContent {



            InstagramCloneTheme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val pagerState = rememberPagerState(initialPage = 1,pageCount = {3})
                val scrollEnabled = currentBackStackEntry?.destination?.route == Screen.Home.route || pagerState.currentPage == 0

                val context = LocalContext.current
                val window = (context as ComponentActivity).window
                window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()
                window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()
                val useDarkIcons = MaterialTheme.colorScheme.background.luminance() > 0.5f

                WindowCompat.getInsetsController(window, window.decorView).apply {
                    isAppearanceLightStatusBars = useDarkIcons
                    isAppearanceLightNavigationBars = useDarkIcons
                }

                HorizontalPager(pagerState, beyondViewportPageCount = 3, userScrollEnabled = scrollEnabled, modifier = Modifier.fillMaxSize().navigationBarsPadding().statusBarsPadding().background(MaterialTheme.colorScheme.background)) {
                    when (it) {
                        0 -> NewPostScreen()
                        1 -> {
                            Scaffold(
                                modifier = Modifier
                                    .fillMaxSize(),
                                bottomBar = { BottomBar(navController, pagerState) },
                            ) { innerPadding ->
                                Navigation(navController, innerPadding, pagerState)

                            }
                        }
                        2 -> ChatsScreen(pagerState)
                    }
                }

            }
        }
    }
}
