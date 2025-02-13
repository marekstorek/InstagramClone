package com.example.instagramclone.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.instagramclone.R
import com.example.instagramclone.data.Dummy

@Composable
fun SearchScreen(){

    var widthPx by remember { mutableIntStateOf(50) }
    var widthDp by remember { mutableStateOf(50.dp) }
    with(LocalDensity.current){
        widthDp = widthPx.toDp()
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .height(35.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(Modifier)
                    Spacer(Modifier)
                    Icon(painterResource(R.drawable.ic_search_24), null, Modifier.padding(10.dp))
                    Text("Search")
                }
            }
            Icon(painterResource(R.drawable.ic_more_horiz_24), null)
            Spacer(Modifier)
        }
        LazyVerticalGrid (
            modifier = Modifier.fillMaxWidth().onGloballyPositioned {
                widthPx = it.size.width
            },
            columns = GridCells.Fixed(3)) {

            items(Dummy.reels.size, key = {Dummy.reels[it % Dummy.reels.size].id }){
                val thumbnail by rememberSaveable { mutableIntStateOf(Dummy.reels[it % Dummy.reels.size].photo) }
                val type by rememberSaveable { mutableIntStateOf((0..1).random()) }
                if (type == 0){
                    SearchPortraitPost(thumbnail)
                } else {
                    val thumbnail2 by rememberSaveable { mutableIntStateOf(Dummy.reels[(it + 10) % Dummy.reels.size].photo) }
                    SearchSquarePost(thumbnail,thumbnail2)
                }
            }
        }
    }
}

@Composable
fun SearchPortraitPost(@DrawableRes photo: Int){
    Image(
        painter = rememberAsyncImagePainter(photo), null,
        Modifier.aspectRatio(1/2f).padding(1.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun SearchSquarePost(@DrawableRes photo: Int,@DrawableRes photo2: Int){
    Column {
        Image(
            painter = rememberAsyncImagePainter(photo), null,
            Modifier.aspectRatio(1f).padding(1.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(photo2), null,
            Modifier.aspectRatio(1f).padding(1.dp),
            contentScale = ContentScale.Crop
        )
    }
}