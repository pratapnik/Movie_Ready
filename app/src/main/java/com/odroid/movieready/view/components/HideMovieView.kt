package com.odroid.movieready.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontRegular
import com.odroid.movieready.theming.primaryButtonTextColor

@Composable
fun HideMovieView(onUnHideClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.82F)
            .fillMaxWidth()
            .background(color = Color(0xFF393E46)),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "movie is hidden",
                style = TextStyle(
                    color = primaryButtonTextColor,
                    fontSize = 20.sp, fontFamily = fontBold
                )
            )
            Spacer(modifier = Modifier.size(24.dp))
            TextButton(onClick = { onUnHideClick.invoke() }) {
                Text(
                    text = "unhide", style = TextStyle(
                        color = Color(0xFFFCE38A),
                        fontSize = 16.sp, fontFamily = fontBold
                    )
                )
            }
        }
    }
}