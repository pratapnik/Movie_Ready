package com.odroid.movieready.view.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.odroid.movieready.R
import com.odroid.movieready.entity.SourceType
import com.odroid.movieready.theming.primaryAppTextColor
import com.odroid.movieready.theming.primaryButtonColor
import com.odroid.movieready.view_intent.EntertainmentCategory
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Preview
@Composable
fun PreviewExploreScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        MoviesExploreScreen(rememberNavController() as DestinationsNavigator, ExploreViewModel())
    }
}

@Destination
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesExploreScreen(
    navigator: DestinationsNavigator,
    exploreViewModel: ExploreViewModel
) {

}
