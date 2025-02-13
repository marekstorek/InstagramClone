package com.example.instagramclone.screens

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.instagramclone.ProfilePhoto
import com.example.instagramclone.R
import com.example.instagramclone.clickableNoEffect
import com.example.instagramclone.data.Dummy
import com.example.instagramclone.data.Message
import com.example.instagramclone.ui.theme.MyMessageColor


@Composable
fun ChatDialog(onDismiss: ()->Unit){

    val messages = Dummy.randomMessages(50)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnBackPress = true)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ){
            ChatDialogTopBar()
            Messages(
                modifier = Modifier.fillMaxWidth().weight(1f),
                messages = messages
            )
            ChatTextField()
        }
    }
}

@Composable
private fun Messages(modifier: Modifier, messages: List<Message>) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        items(messages.size - 1) {

            val isPreviousTheSame = try {
                messages[it - 1].user == messages[it].user
            } catch (e: IndexOutOfBoundsException) {
                false
            }

            val isNextTheSame = try {
                messages[it + 1].user == messages[it].user
            } catch (e: IndexOutOfBoundsException) {
                false
            }

            Message(
                text = messages[it].message,
                isPreviousTheSame = isPreviousTheSame,
                isNextTheSame = isNextTheSame,
                person = messages[it].user
            )
        }
    }
}

@Composable
private fun ChatTextField() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(bottom = 10.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier)
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xff4a5df9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(R.drawable.ic_camera_filled_24),
                    null,
                    Modifier.padding(1.dp),
                    tint = Color.White
                )
            }
            Spacer(Modifier)
            Text("Message...", modifier = Modifier.weight(1f), color = Color.Gray)

            Icon(painterResource(R.drawable.ic_mic_24), null, Modifier.padding(10.dp))
            Icon(painterResource(R.drawable.ic_picture_24), null, Modifier.padding(10.dp))
            Icon(painterResource(R.drawable.ic_sticker_24), null, Modifier.padding(10.dp))
            Spacer(Modifier)
            Spacer(Modifier)
        }
    }
}


@Composable
private fun ChatDialogTopBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 5.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){

            val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
            Icon(
                painter = painterResource(R.drawable.ic_back_24),
                contentDescription = null,
                modifier = Modifier.clickableNoEffect {
                    onBackPressedDispatcher?.onBackPressed()
                }
            )

            Spacer(Modifier.width(20.dp))
            ProfilePhoto(
                status = 3,
                size = 30.dp,
                photo = Dummy.profilePhotos.random()
            )
            Spacer(Modifier.width(10.dp))

            Column(Modifier.weight(1f)) {
                Text(text = Dummy.names.random(), color = MaterialTheme.colorScheme.onBackground, fontSize = 15.sp)
                Text(text = Dummy.usernames.random(), color = Color.Gray, fontSize = 14.sp)
            }
            Icon(painter = painterResource(R.drawable.ic_call_24), contentDescription = null)
            Spacer(Modifier.width(20.dp))
            Icon(painter =painterResource(R.drawable.ic_video_call_24), contentDescription = null)
        }
    }
}

@Composable
private fun Message(text: String, isPreviousTheSame: Boolean, isNextTheSame: Boolean, person: Int){

    var topStart = 15.dp
    var topEnd = 15.dp
    var bottomStart = 15.dp
    var bottomEnd = 15.dp

    val color: Color

    var padding = 12.dp

    // setting rounded corner shape values based on whose message
    // it is and if previous or next message is from the same person
    if (person == 0){
        color = MyMessageColor // my message
        if (isPreviousTheSame){
            bottomEnd = 0.dp
            padding = 1.dp
        }
        if (isNextTheSame){
            topEnd = 0.dp
        }
    } else {
        color = MaterialTheme.colorScheme.surface // friend's message
        if (isPreviousTheSame){
            bottomStart = 0.dp
            padding = 1.dp
        }
        if (isNextTheSame){
            topStart = 0.dp
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(bottom = padding) // creates extra space between user's and friend's messages
    ) {
        if (person == 0){ // These two spacers ensure alignment of the message to the right side
            Spacer(Modifier.weight(1f))
        }
        Box( // Message itself
            modifier = Modifier
                .widthIn(40.dp, ((170..270).random()).dp)
                .heightIn(min = 40.dp)
                .padding(horizontal = 8.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = topStart,
                        topEnd = topEnd,
                        bottomStart = bottomStart,
                        bottomEnd = bottomEnd
                    )
                )
                .background(color)
        ){
            Text(
                text = text,
                modifier = Modifier.padding(8.dp)
            )
        }
        if (person == 1){ // These two spacers ensure alignment of the message to the right side
            Spacer(Modifier.weight(1f))
        }
    }

}