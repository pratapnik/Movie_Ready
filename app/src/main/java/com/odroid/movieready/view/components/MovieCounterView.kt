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
import com.odroid.movieready.theming.*

@Composable
fun MovieCounterView(value: Int) {
    Box(
        modifier = Modifier
            .defaultMinSize(40.dp, 40.dp)
            .background(color = radicalRed, shape = CircleShape)
            .padding(start = 9.dp, end = 9.dp, top = 4.dp, bottom = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "#$value", style = TextStyle(
                    color = primaryDarkModeAppTextColor,
                    fontSize = 17.sp, fontFamily = fontBold
                )
            )
        }

    }
}