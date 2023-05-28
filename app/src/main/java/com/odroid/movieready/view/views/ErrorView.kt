package com.odroid.movieready.view.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odroid.movieready.R
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontRegular
import com.odroid.movieready.view.components.ButtonIcon
import com.odroid.movieready.view.components.CommonButton

@Preview
@Composable
fun PreviewErrorView() {
    ErrorView(tryAgain = {})
}

@Composable
fun ErrorView(tryAgain: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = IshaaraColors.background_color_FFFFFF)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = "Something went wrong",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = stringResource(id = R.string.error_title), style = TextStyle(
                    color = IshaaraColors.primary_app_error_text_color,
                    fontSize = 28.sp,
                    fontFamily = fontBold
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = stringResource(id = R.string.error_subtitle), style = TextStyle(
                    color = IshaaraColors.primary_app_dark_text_color,
                    fontSize = 16.sp,
                    fontFamily = fontRegular
                )
            )
            Spacer(modifier = Modifier.size(32.dp))
            CommonButton(
                onClick = { tryAgain.invoke() },
                label = stringResource(id = R.string.retry_button_label),
                prefixIcon = ButtonIcon(
                    icon = R.drawable.ic_retry,
                    iconSize = 24.dp,
                    iconSpacing = 10.dp,
                    iconColor = IshaaraColors.background_color_FFFFFF
                ),
                modifier = Modifier
                    .height(48.dp),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp
                )
            )
        }
    }

}