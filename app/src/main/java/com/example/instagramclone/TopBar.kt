package com.example.instagramclone

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun TopBar(pagerState: PagerState){

    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background).padding(vertical = 20.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        Row(
            modifier = Modifier.clickableNoEffect {

            }
        ){
            Text("For You", fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(painterResource(R.drawable.ic_heart_24), contentDescription = null)
            Icon(painterResource(R.drawable.ic_share_24), contentDescription = null, modifier = Modifier.clickableNoEffect {
                scope.launch {
                    pagerState.animateScrollToPage(2, animationSpec = tween(durationMillis = 800))
                }
            })
        }
    }

}