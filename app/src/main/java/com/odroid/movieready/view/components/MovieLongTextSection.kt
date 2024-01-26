package com.odroid.movieready.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odroid.movieready.model.MovieSuggestionModel
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.fontBold

@Preview
@Composable
fun PreviewMovieLongTextSection() {
    MovieLongTextSection(
        title = "Overview",
        description = MovieSuggestionModel.preview.summary,
        shouldShowFull = true,
        maxLines = 4
    )
}

@Composable
fun MovieLongTextSection(
    title: String,
    description: String,
    shouldShowFull: Boolean,
    maxLines: Int
) {
    val maxLinesForDescription = if (shouldShowFull) {
        Int.MAX_VALUE
    } else {
        maxLines
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title, style = TextStyle(
                color = IshaaraColors.primary_app_dark_text_color,
                fontSize = 24.sp,
                fontFamily = fontBold,
                lineHeight = 28.sp
            )
        )
        Spacer(modifier = Modifier.size(12.dp))
        ExpandingText(text = description, minimizedMaxLines = 4)
    }

}