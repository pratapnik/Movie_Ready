package com.odroid.movieready.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.primaryAppTextColor

@Composable
fun MovieCounterView(value: Int) {
    Box(
        modifier = Modifier
            .defaultMinSize(50.dp, 50.dp)
            .background(color = Color(0xFFFFEA20), shape = CircleShape)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row() {
            Text(
                text = "movie count:", style = TextStyle(
                    color = primaryAppTextColor,
                    fontSize = 12.sp, fontFamily = fontBold
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = value.toString(), style = TextStyle(
                    color = primaryAppTextColor,
                    fontSize = 16.sp, fontFamily = fontBold
                )
            )
        }

    }
}