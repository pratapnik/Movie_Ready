package com.odroid.movieready.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.IshaaraShapes
import com.odroid.movieready.theming.fontMedium
import com.odroid.movieready.util.ViewUtil

@Composable
fun MoviesHorizontalCardSection(title: String, items: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title, style = TextStyle(
                color = IshaaraColors.primary_app_light_text_color,
                fontSize = 24.sp,
                fontFamily = fontMedium,
                lineHeight = 28.sp
            )
        )
        Spacer(modifier = Modifier.size(12.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { item ->
                Box(
                    modifier = Modifier
                        .width(148.dp)
                        .height(164.dp)
                        .background(
                            color = Color(0xFF252525),
                            shape = IshaaraShapes.default.roundedCornerMedium
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = ViewUtil.getNameInitials(item, 2, shouldCapitalize = true),
                            style = TextStyle(
                                color = IshaaraColors.unknown_color_53E88B,
                                fontSize = 28.sp,
                                fontFamily = fontMedium,
                                lineHeight = 28.sp
                            )
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = item,
                            style = TextStyle(
                                color = IshaaraColors.primary_app_light_text_color,
                                fontSize = 16.sp,
                                fontFamily = fontMedium
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}