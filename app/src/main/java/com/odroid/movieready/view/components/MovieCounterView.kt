package com.odroid.movieready.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
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
import com.odroid.movieready.theming.scarpaFlowBorderColor

@Composable
fun MovieCounterView(value: Int) {
    Box(
        modifier = Modifier
            .defaultMinSize(40.dp, 40.dp)
            .background(color = Color(0xFF96c93d), shape = CircleShape)
            .border(width = 1.dp, shape = CircleShape, color = scarpaFlowBorderColor)
            .padding(vertical = 4.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "#$value", style = TextStyle(
                    color = primaryAppTextColor,
                    fontSize = 17.sp, fontFamily = fontBold
                )
            )
        }
    }
}