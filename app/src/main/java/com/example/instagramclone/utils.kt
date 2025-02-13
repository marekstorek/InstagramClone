package com.example.instagramclone

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.clickableNoEffect(onClick: ()->Unit): Modifier {
    val modifier = this.
    clickable(
        interactionSource = null,
        indication = null,
        onClick = {
            onClick()
        }
    )
    return modifier
}

fun Color.inverted(): Color {
    val color: Color = this.copy(
        red = 1f-this.red,
        blue = 1f-this.blue,
        green = 1f-this.green,
    )
    return color
}