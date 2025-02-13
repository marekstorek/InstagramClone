package com.example.instagramclone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.instagramclone.data.Dummy
import com.example.instagramclone.screens.Screen

@Composable
fun ReelSuggestion(navController: NavHostController){
    Column(Modifier.padding(vertical = 10.dp)) {
        Text("Suggested reels", modifier = Modifier.padding(horizontal = 10.dp).padding(bottom = 5.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            item{
                Spacer(Modifier)
            }
            items(Dummy.reels){ reel->
                val drawable = reel.photo
                Image(
                    painter = rememberAsyncImagePainter(drawable),
                    null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(162.dp).height(254.dp).clip(RoundedCornerShape(10.dp)).clickableNoEffect {
                        navController.navigate(Screen.Reels.route)
                    }
                )
            }
            item{
                Spacer(Modifier)
            }
        }
    }
}

