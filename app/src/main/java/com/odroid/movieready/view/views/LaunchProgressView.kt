package com.odroid.movieready.view.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.odroid.movieready.R
import com.odroid.movieready.theming.primaryAppTextColor

@Composable
fun LaunchProgressView() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_red_drop))
    val lottieAnimation by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = false
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            composition = composition,
            progress = lottieAnimation,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = "Ishaara curating movies for you",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.font_bold)),
                fontSize = 20.sp,
                color = primaryAppTextColor,
                letterSpacing = 1.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(horizontal = 100.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLaunchProgressView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LaunchProgressView()
    }
}