package com.example.instagramclone

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun SelectableIcon(@DrawableRes defaultIcon: Int,@DrawableRes selectedIcon: Int, tint: Color = LocalContentColor.current, defaultTint: Color = LocalContentColor.current){
    var isSelected by rememberSaveable {mutableStateOf(false)}

    val drawable = if (isSelected) selectedIcon else defaultIcon

    Icon(
        modifier = Modifier.clickableNoEffect {
            isSelected = !isSelected
        },
        painter = painterResource(drawable),
        contentDescription = null,
        tint = if (isSelected) tint else defaultTint
    )
}

