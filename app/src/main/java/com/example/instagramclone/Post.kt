package com.example.instagramclone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.instagramclone.data.Post

@Composable

fun Post(post: Post){
    Column {
        val modifier = Modifier.padding(horizontal = 10.dp).padding(bottom = 5.dp)
        PostHeader(modifier = modifier, post = post)
        Image(painterResource(post.photo), null, contentScale = ContentScale.Fit)
        PostFooter(post)
        Spacer(Modifier.height(15.dp))
    }
}

@Composable

fun PostHeader(extraMessageLeft: String? = null, extraMessageRight: String? = null, modifier: Modifier = Modifier, post: Post){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            ProfilePhoto(0, 45.dp, post.profilePhoto)
            Spacer(Modifier.width(5.dp))
            Text(post.username)
            if (!extraMessageLeft.isNullOrEmpty()){
                Spacer(Modifier.width(8.dp))
                Text(extraMessageLeft)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            if (!extraMessageRight.isNullOrEmpty()){
                Text(extraMessageRight)
                Spacer(Modifier.width(8.dp))
            }
            Icon(
                painterResource(R.drawable.ic_more_vert_24),
                contentDescription = null
            )

        }
    }
}
@Composable
fun PostFooter(post: Post){
    Column {
        PostFooterIconRow(post)
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append(post.username)
                }
                append (" " + post.description)
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun PostFooterIconRow(post: Post){
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SelectableIcon(R.drawable.ic_heart_24,R.drawable.ic_heart_filled_24, Color.Red)
        Spacer(Modifier.size(0.dp))

        Text(post.likes.toString())
        Spacer(Modifier.size(5.dp))

        Icon(painterResource(R.drawable.ic_comment_24), null)
        Spacer(Modifier.size(0.dp))

        Text(post.comments.toString())
        Spacer(Modifier.size(5.dp))

        Icon(painterResource(R.drawable.ic_share_24), null)
        Spacer(modifier = Modifier.weight(1f))
        SelectableIcon(R.drawable.ic_save_24,R.drawable.ic_save_filled_24)

    }
}

