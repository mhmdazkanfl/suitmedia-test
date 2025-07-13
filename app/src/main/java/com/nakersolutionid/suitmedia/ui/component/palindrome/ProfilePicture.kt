package com.nakersolutionid.suitmedia.ui.component.palindrome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart
) {
    Box(modifier = modifier
        .size(140.dp)
        .background(
            color = Color.White.copy(alpha = 0.5f),
            shape = CircleShape
        ),
        contentAlignment = contentAlignment) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Filled.Person,
            contentDescription = null,
            tint = Color.White
        )
    }
}