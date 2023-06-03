package com.odroid.movieready.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.IshaaraShapes
import com.odroid.movieready.theming.fontMedium
import com.odroid.movieready.theming.fontRegular

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieChipsSection(title: String, items: List<String>) {
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
        FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items.forEach {
                Box(
                    modifier = Modifier
                        .background(
                            color = IshaaraColors.unknown_color_15BE77_20_percent_opacity,
                            shape = IshaaraShapes.default.roundedCornerLarge
                        )
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = it, style = TextStyle(
                            color = IshaaraColors.unknown_color_53E88B,
                            fontSize = 16.sp,
                            fontFamily = fontRegular,
                        )
                    )
                }
            }
        }
    }
}