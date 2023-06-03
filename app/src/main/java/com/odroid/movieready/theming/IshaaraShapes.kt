package com.odroid.movieready.theming

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
class IshaaraShapes(
    val circle: CornerBasedShape,
    val roundedCornerX3Small: CornerBasedShape,
    val roundedCornerX2Small: CornerBasedShape,
    val roundedCornerXXSmall: CornerBasedShape,
    val roundedCornerXSmall: CornerBasedShape,
    val roundedCornerSmall: CornerBasedShape,
    val roundedCornerNormal: CornerBasedShape,
    val roundedCorner2Normal: CornerBasedShape,
    val roundedCornerMedium: CornerBasedShape,
    val roundedCornerLarge: CornerBasedShape,
    val roundedCornerXLarge: CornerBasedShape,

    ) {
    companion object {
        val default = IshaaraShapes(
            circle = CircleShape,
            roundedCornerXXSmall = RoundedCornerShape(4.dp),
            roundedCornerX3Small = RoundedCornerShape(6.dp),
            roundedCornerX2Small = RoundedCornerShape(7.dp),
            roundedCornerXSmall = RoundedCornerShape(8.dp),
            roundedCornerSmall = RoundedCornerShape(10.dp),
            roundedCornerNormal = RoundedCornerShape(12.dp),
            roundedCorner2Normal = RoundedCornerShape(13.dp),
            roundedCornerMedium = RoundedCornerShape(15.dp),
            roundedCornerLarge = RoundedCornerShape(20.dp),
            roundedCornerXLarge = RoundedCornerShape(40.dp),
        )
    }
}