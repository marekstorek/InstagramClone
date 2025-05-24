package com.example.instagramclone.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.instagramclone.PostHeader
import com.example.instagramclone.R
import com.example.instagramclone.data.Dummy
import com.example.instagramclone.data.Dummy.posts
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StoryDialog(onDismiss: ()->Unit){
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnBackPress = true)) {
        MaterialTheme(colorScheme = darkColorScheme()) {
            val pagerState = rememberPagerState { 15 }
            HorizontalPager(pagerState, beyondViewportPageCount = 2, modifier = Modifier.fillMaxSize()) {
                val swipeThreshold = 100f // Minimum distance to consider as a swipe down
                var totalDrag by remember { mutableFloatStateOf(0f) }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectVerticalDragGestures(
                                onVerticalDrag = { _, dragAmount ->
                                    if (dragAmount > 0) { // Only track downward drag
                                        totalDrag += dragAmount
                                    }
                                },
                                onDragEnd = {
                                    if (totalDrag > swipeThreshold) {
                                        onDismiss()
                                    }
                                    totalDrag = 0f // Reset after gesture ends
                                },
                                onDragCancel = {
                                    totalDrag = 0f
                                }
                            )
                        }
                        .background(MaterialTheme.colorScheme.background)
                        .padding(vertical = 20.dp)
                ) {
                    StoryDialogContent(it, pagerState, onDismiss, Modifier.weight(1f))
                    StoryDialogBottomRow()
                }
            }
        }
    }
}

@Composable
private fun StoryDialogContent(
    page: Int,
    pagerState: PagerState,
    onDismiss: () -> Unit,
    modifier: Modifier
) {
    val timeAgo by rememberSaveable { mutableIntStateOf((2..59).random()) }

    Box(modifier) {
        Image(
            painter = painterResource(Dummy.reelThumbnails[page % Dummy.reelThumbnails.size]),
            null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Spacer(Modifier.height(5.dp))
            StoryDialogProgressBar(pagerState, page, onDismiss)
            Spacer(Modifier.height(10.dp))
            PostHeader(
                extraMessageLeft = "$timeAgo min",
                post = posts[page],
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
    }
}

@Composable
private fun StoryDialogProgressBar(
    pagerState: PagerState,
    page: Int,
    onDismiss: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    var progress by remember { mutableFloatStateOf(0f) }
    if (pagerState.currentPage == page) {
        LaunchedEffect(Unit) {
            progress = 0f
            while (progress < 1f) {
                progress += 0.005f
                delay(30)
            }
            if (pagerState.currentPage + 1 == pagerState.pageCount) {
                onDismiss()
            } else {
                scope.launch {
                    pagerState.animateScrollToPage(
                        page + 1,
                        animationSpec = tween(durationMillis = 500)
                    )
                }
            }
        }
    }
    LinearProgressIndicator(
        progress = { progress }, Modifier
            .fillMaxWidth()
            .height(2.dp)
    )
}

@Composable
private fun StoryDialogBottomRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StoryDialogTextField(modifier = Modifier.weight(1f))
        Icon(
            painterResource(R.drawable.ic_heart_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
        Icon(
            painterResource(R.drawable.ic_share_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.width(5.dp))
    }
}

@Composable
private fun StoryDialogTextField(modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
            .height(55.dp)
            .border(
                width = 1.5.dp,
                Color.Gray,
                RoundedCornerShape(27.dp)
            )
            .clip(RoundedCornerShape(27.dp)),

        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            "Send message",
            modifier = Modifier.padding(start = 20.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

