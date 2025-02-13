package com.example.instagramclone

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

/**
 *  status:
 *  0 - Nothing;
 *  1 - Normal Story;
 *  2 - Close Friends;
 *  3 - Already Viewed Story*/
@Composable
fun ProfilePhoto(
    status: Int,
    size: Dp = 90.dp,
    @DrawableRes photo: Int
){
    val brush = when(status){
        1->Brush.sweepGradient(
            listOf(
                Color(0xFFD62976),
                Color(0xFFFEDA75),
                Color(0xFFFA7E1E),
                Color(0xFFD62976)
            )
        )
        2->SolidColor(Color(0xff71d53f))
        3->SolidColor(Color.Gray)
        else->SolidColor(Color.Transparent)
    }

    Image(
        modifier = Modifier
            .size(size)
            .border( // outer border
                width = size / 35,
                brush = brush,
                shape = CircleShape
            )
            .padding(size/15)
            .border( // thin gray inner border
                width = size / 300,
                color = Color.Gray,
                shape = CircleShape
            )
            .clip(CircleShape),
        painter = rememberAsyncImagePainter(photo),
        contentDescription = null,

        contentScale = ContentScale.Crop,
    )
}

@Composable
fun UserProfilePhoto(
    status: Int,
    @DrawableRes photo: Int
){

        Box(
            contentAlignment = Alignment.BottomEnd
        ){
            ProfilePhoto(status, photo = photo)
            Box(modifier = Modifier.padding(10.dp).clip(CircleShape).background(MaterialTheme.colorScheme.background.inverted())){
                Icon(
                    modifier = Modifier
                        .border(
                            width = 0.dp,
                            color = Color.White,
                            shape = CircleShape
                        ).size(25.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
}


