package com.odroid.movieready.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.odroid.movieready.theming.IshaaraColors

@Composable
fun IshaaraBottomSheetScaffold(
    modifier: Modifier,
    isTopNotchVisible: Boolean,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    bottomEnd = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp),
                    topStart = CornerSize(32.dp),
                    topEnd = CornerSize(32.dp)
                )
            )
    ) {

        content.invoke()

        if (isTopNotchVisible) {
            Divider(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 16.dp)
                    .size(width = 50.dp, height = 5.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.TopCenter),
                color = IshaaraColors.unknown_color_FEF6ED_20,
                thickness = 5.dp,
            )
        }
    }
}