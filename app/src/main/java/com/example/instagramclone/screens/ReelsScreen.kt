package com.example.instagramclone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.ProfilePhoto
import com.example.instagramclone.R
import com.example.instagramclone.SelectableIcon
import com.example.instagramclone.data.Dummy
import com.example.instagramclone.data.Post
import kotlinx.coroutines.delay

@Composable
fun ReelsScreen(){
    val pagerState = rememberPagerState { Dummy.reels.size }
    VerticalPager(
        pagerState,
        beyondViewportPageCount = 3
    ) {
        Reel(Dummy.reels[it], pagerState, it)
    }
}

@Composable
fun Reel(reel: Post, pagerState: PagerState, page : Int){
    Box(Modifier.fillMaxSize()) {
        Image(painterResource(reel.photo),null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        ReelsIcons(reel, page)
        var progress by remember { mutableFloatStateOf(0f) }
        if (pagerState.currentPage == page) {
            LaunchedEffect(Unit) {
                progress = 0f
                while (true) {
                    progress += 0.005f
                    delay(30)
                }
            }
        }
        LinearProgressIndicator(
            progress = {
                progress % 1f
            },
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun ReelsIcons(reel: Post, page: Int){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxHeight()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ProfilePhoto(0,50.dp, Dummy.profilePhotos[page % Dummy.profilePhotos.size])
                Spacer(Modifier.width(5.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)){
                            append(reel.username)
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Thin, fontSize = 12.sp)){
                            append("\n" + "Original audio")
                        }
                    },
                    color = Color.White,
                    lineHeight = 15.sp
                )
                Spacer(Modifier.width(15.dp))
                MyButton  {
                    Text("Follow", Modifier.padding(vertical = 2.dp, horizontal = 15.dp), color = Color.White)
                }
            }
            Text(reel.description, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.White, modifier = Modifier.widthIn(max = 200.dp))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Icon(painterResource(R.drawable.ic_search_24),null, tint = Color.White)
            Spacer(Modifier.weight(1f))
            SelectableIcon(R.drawable.ic_heart_24, R.drawable.ic_heart_filled_24, tint = Color.Red,defaultTint = Color.White)
            Spacer(Modifier)
            Text(reel.likes.toString(), color = Color.White)
            Spacer(Modifier)
            Spacer(Modifier)
            Icon(painterResource(R.drawable.ic_comment_24),null, tint = Color.White)
            Spacer(Modifier)
            Text(reel.comments.toString(), color = Color.White)
            Spacer(Modifier)
            Spacer(Modifier)
            Icon(painterResource(R.drawable.ic_share_24),null, tint = Color.White)
            Spacer(Modifier)
            Text(reel.shares.toString(), color = Color.White)
            Spacer(Modifier)
            Spacer(Modifier)
            Icon(painterResource(R.drawable.ic_more_vert_24),null, tint = Color.White)
        }
    }
}