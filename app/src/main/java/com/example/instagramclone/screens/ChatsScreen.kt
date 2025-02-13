package com.example.instagramclone.screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.ProfilePhoto
import com.example.instagramclone.R
import com.example.instagramclone.clickableNoEffect
import com.example.instagramclone.data.Dummy
import kotlinx.coroutines.launch

@Composable
fun ChatsScreen(pagerState: PagerState) {
    Surface{
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            ChatScreenTopBar(pagerState = pagerState)
            ChatScreenSearchBar()
            NotesBar()
            MessagesAndRequestsRow()
            repeat(20){
                ChatItem(Dummy.profilePhotos[it % Dummy.profilePhotos.size], Dummy.names[it % Dummy.names.size])
            }
        }
    }
}

@Composable
private fun MessagesAndRequestsRow() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .padding(vertical = 10.dp, horizontal = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Messages", Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
        Text(
            "Requests",
            color = MaterialTheme.colorScheme.onTertiary,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ChatScreenSearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp, bottom = 20.dp)
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
            Icon(
                painter = painterResource(R.drawable.ic_search_24),
                contentDescription = null,
                modifier = Modifier.padding(10.dp)
            )
            Text("Search")
        }
    }

}

@Composable
private fun ChatScreenTopBar(pagerState: PagerState){
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
       Icon(
           painter = painterResource(R.drawable.ic_back_24),
           contentDescription = null,
           modifier = Modifier.clickableNoEffect {
               scope.launch {
                   pagerState.animateScrollToPage(pagerState.currentPage-1, animationSpec = tween(durationMillis = 500))
               }
           }
       )
       Spacer(Modifier.width(20.dp))

       Text(Dummy.myProfile.username, fontWeight = FontWeight.Bold, fontSize = 20.sp)
       Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
       Spacer(Modifier.weight(1f))

       Icon(painterResource(R.drawable.ic_discover_channel_24), contentDescription = null)
       Spacer(Modifier.width(20.dp))
       Icon(painterResource(R.drawable.ic_new_message_24), contentDescription = null)
    }
}

@Composable
private fun NotesBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.width(7.dp))
        repeat(15){
            NotesBarItem(Dummy.profilePhotos[it % Dummy.profilePhotos.size], Dummy.names[it % Dummy.names.size], Dummy.notes[it % Dummy.notes.size])
        }
        Spacer(Modifier)
    }
}

@Composable
private fun NotesBarItem(@DrawableRes profilePhoto: Int, name: String, message: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.padding(horizontal = 7.5.dp),
            contentAlignment = Alignment.TopCenter
        ){
            Box(Modifier.padding(top = 30.dp)){
                ProfilePhoto(0, 80.dp, photo = profilePhoto)
            }
            Box(
                modifier = Modifier.height(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .width(70.dp)
                        .heightIn(20.dp, 70.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Text(
                        message,
                        modifier = Modifier.padding(10.dp).fillMaxWidth(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 10.sp,
                        lineHeight = 10.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
        Text(
            name,
            modifier = Modifier.widthIn(max = 85.dp),
            fontWeight = FontWeight.Thin,
            fontSize = 10.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ChatItem(@DrawableRes photo: Int, userName: String){
    var show by remember { mutableStateOf(false) }
    val status by rememberSaveable { mutableIntStateOf((0..3).random()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .clickable{ show = !show },
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (show){
            ChatDialog(onDismiss = {show = !show})
        }
        Spacer(Modifier)
        ProfilePhoto(status, 65.dp, photo = photo)
        Column(Modifier.weight(1f)) {
            Text(userName, color = MaterialTheme.colorScheme.onBackground, fontSize = 15.sp)
            Text("Active now", color = Color.Gray, fontSize = 14.sp)
        }
        Icon(painterResource(R.drawable.ic_camera_24),null)
        Spacer(Modifier)
        Spacer(Modifier)
    }
}