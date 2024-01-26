package com.odroid.movieready.view.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.fontRegular

@Composable
fun ExpandingText(modifier: Modifier = Modifier, text: String, minimizedMaxLines: Int) {
    var isExpanded = remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable = remember { mutableStateOf(false) }
    var finalText = remember { mutableStateOf(buildAnnotatedString { append(text) }) }
    val interactionSource = remember { MutableInteractionSource() }
    val textLayoutResult = textLayoutResultState.value
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded.value -> {
                finalText.value = buildAnnotatedString {
                    append(text)
                    withStyle(
                        style = TextStyle(
                            color = Color(0xFFf28705),
                            fontSize = 16.sp,
                            fontFamily = fontRegular,
                            lineHeight = 21.sp
                        ).toSpanStyle()
                    ) {
                        append(" show less")
                    }
                }
            }

            !isExpanded.value && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(minimizedMaxLines - 1)
                val showMoreString = "...show more"
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText.value = buildAnnotatedString {
                    append(adjustedText)
                    withStyle(
                        style = TextStyle(
                            color = Color(0xFFf28705),
                            fontSize = 16.sp,
                            fontFamily = fontRegular,
                            lineHeight = 21.sp
                        ).toSpanStyle()
                    ) {
                        append(showMoreString)
                    }
                }

                isClickable.value = true
            }
        }
    }

    Text(
        text = finalText.value,
        maxLines = if (isExpanded.value) Int.MAX_VALUE else minimizedMaxLines,
        onTextLayout = { textLayoutResultState.value = it },
        modifier = modifier
            .clickable(
                enabled = isClickable.value,
                interactionSource = interactionSource,
                indication = null
            ) { isExpanded.value = !isExpanded.value }
            .animateContentSize(),
        style = TextStyle(
            color = IshaaraColors.primary_app_dark_text_color,
            fontSize = 16.sp,
            fontFamily = fontRegular,
            lineHeight = 21.sp
        )
    )
}