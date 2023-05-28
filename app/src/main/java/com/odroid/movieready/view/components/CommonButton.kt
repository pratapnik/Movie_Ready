package com.odroid.movieready.view.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odroid.movieready.R
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.IshaaraShapes
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.primaryButtonTextColor

@Preview
@Composable
fun PreviewCommonButton() {
    Box(
        modifier = Modifier
            .background(color = IshaaraColors.background_color_FFFFFF)
            .padding(40.dp)
    ) {
        CommonButton(
            modifier = Modifier
                .width(140.dp)
                .height(64.dp), label = "start",
            suffixIcon = ButtonIcon(
                icon = R.drawable.ic_next,
                iconSize = 24.dp,
                iconSpacing = 10.dp
            )
        )
    }
}


@Composable
fun CommonButton(
    modifier: Modifier,
    label: String = "",
    prefixIcon: ButtonIcon = ButtonIcon.default,
    suffixIcon: ButtonIcon = ButtonIcon.default,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = IshaaraColors.app_button_background_primary
        ),
        shape = IshaaraShapes.default.roundedCornerMedium
    ) {
        if (prefixIcon.icon != 0) {
            Icon(
                painter = painterResource(id = prefixIcon.icon), contentDescription = "",
                modifier = Modifier
                    .padding(end = prefixIcon.iconSpacing)
                    .size(prefixIcon.iconSize), tint = prefixIcon.iconColor
            )
        }
        Text(
            text = label, modifier = Modifier.shadow(elevation = 24.dp), style = TextStyle(
                color = primaryButtonTextColor,
                fontSize = 20.sp, fontFamily = fontBold
            )
        )
        if (suffixIcon.icon != 0) {
            Icon(
                painter = painterResource(id = suffixIcon.icon), contentDescription = "",
                modifier = Modifier
                    .padding(start = suffixIcon.iconSpacing)
                    .size(suffixIcon.iconSize), tint = suffixIcon.iconColor
            )
        }
    }
}

@Immutable
data class ButtonIcon(
    @DrawableRes val icon: Int,
    val iconSpacing: Dp,
    val iconSize: Dp,
    val iconColor: Color = Color.Unspecified
) {
    companion object {
        val default = ButtonIcon(
            icon = 0,
            iconSpacing = 4.dp,
            iconSize = 16.dp
        )
    }
}