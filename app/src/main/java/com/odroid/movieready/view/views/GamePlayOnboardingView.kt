package com.odroid.movieready.view.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.odroid.movieready.R
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.view.components.ButtonIcon
import com.odroid.movieready.view.components.CommonButton

@Preview
@Composable
fun PreviewGamePlayOnboardingView() {
    GamePlayOnboardingView(onStartClick = {})
}

@Composable
fun GamePlayOnboardingView(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = IshaaraColors.background_color_FFFFFF),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopGreeting(
            shouldShowExploreButton = false,
            onExploreButtonClick = {

            }
        )
        Image(
            painter = painterResource(id = R.drawable.shut_up_image),
            contentDescription = "Don't show the movies to others",
            modifier = Modifier.height(
                400.dp
            )
        )
        CommonButton(
            onClick = { onStartClick.invoke() },
            label = stringResource(id = R.string.lets_play_label),
            suffixIcon = ButtonIcon(
                icon = R.drawable.ic_next,
                iconSize = 24.dp,
                iconSpacing = 10.dp
            ),
            modifier = Modifier
                .width(140.dp)
                .height(56.dp)
        )
        Spacer(modifier = Modifier.size(40.dp))
    }
}