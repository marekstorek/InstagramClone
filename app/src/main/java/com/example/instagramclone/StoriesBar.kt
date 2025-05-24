package com.example.instagramclone

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramclone.data.Dummy
import com.example.instagramclone.screens.StoryDialog

@Composable
fun StoriesBar(){
    var showStory by rememberSaveable { mutableStateOf(false) }
    if (showStory){
        StoryDialog { showStory = !showStory }
    }
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Spacer(Modifier.size(0.dp))
        }
        item {
            UserStoriesBarItem()
        }
        items(15){
            StoriesBarItem(if (it in 0..1) 2 else if (it in 2..9) 1 else 0,onClick = {showStory = !showStory}, Dummy.profilePhotos[it % Dummy.profilePhotos.size],
                Dummy.usernames[it % Dummy.usernames.size])
        }
        item {
            Spacer(Modifier.size(0.dp))
        }
    }
}

/**
 * Regular item in stories bar*/
@Composable
fun StoriesBarItem(status: Int = 0, onClick: ()->Unit = {}, @DrawableRes photo: Int, username: String){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickableNoEffect {
        onClick()
    }) {
        ProfilePhoto(status, photo = photo)
        Text(username, modifier = Modifier.widthIn(max = 85.dp), fontSize = 14.sp, fontWeight = FontWeight.Normal, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

/**
 * User stories bar item with plus+ icon*/
@Composable
fun UserStoriesBarItem(status: Int = 0){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        UserProfilePhoto(status, photo = Dummy.myProfile.profilePhoto)
        Text(Dummy.myProfile.username, modifier = Modifier.widthIn(max = 85.dp), fontSize = 14.sp, fontWeight = FontWeight.Normal, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

