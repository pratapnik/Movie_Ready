package com.odroid.movieready.view.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odroid.movieready.model.MovieSuggestionModel
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.fontRegular
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle

@Destination(style = DestinationStyle.BottomSheet::class)
@Composable
fun MovieDetailsModal(movieSuggestionModel: MovieSuggestionModel) {
    Box(
        modifier = Modifier
            .heightIn(max = (LocalConfiguration.current.screenHeightDp * 0.85).dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = movieSuggestionModel.movieTitle, style = TextStyle(
                    color = IshaaraColors.primary_app_dark_text_color,
                    fontSize = 16.sp,
                    fontFamily = fontRegular
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = movieSuggestionModel.imdbRating, style = TextStyle(
                    color = IshaaraColors.primary_app_dark_text_color,
                    fontSize = 16.sp,
                    fontFamily = fontRegular
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = movieSuggestionModel.summary, style = TextStyle(
                    color = IshaaraColors.primary_app_dark_text_color,
                    fontSize = 16.sp,
                    fontFamily = fontRegular
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = movieSuggestionModel.story, style = TextStyle(
                    color = IshaaraColors.primary_app_dark_text_color,
                    fontSize = 16.sp,
                    fontFamily = fontRegular
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}
