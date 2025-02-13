package com.example.instagramclone

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.instagramclone.data.Dummy
import com.example.instagramclone.data.Post

@Composable
fun ThreadsBlock(){
    Column {
        PostHeader(
            extraMessageRight = "Open app",
            post = Dummy.posts[0].copy(profilePhoto = R.drawable.profile_threads, username = "Threads"),
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 5.dp)
        )
        ThreadsPagerContainer()
    }

}

@Composable
fun ThreadsPagerContainer(){
    val pagerState = rememberPagerState { 10 }
    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            beyondViewportPageCount = 2
        ) {
            ThreadsCard(Dummy.postsGroup3[it % Dummy.postsGroup3.size])
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.Blue else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(4.dp)
                )
            }
        }
    }
}

@Composable
fun ThreadsCard(post: Post){
    Card(
        modifier = Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(5.dp)),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
        ) {
            PostHeader(extraMessageRight = "9 h", post = post)
            Text(
                post.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(5.dp).height(55.dp))
            Image(
                painter = painterResource(post.photo),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(16/9f)
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}