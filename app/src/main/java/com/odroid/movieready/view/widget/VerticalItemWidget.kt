package com.odroid.movieready.view.widget

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontRegular
import com.odroid.movieready.theming.primaryAppTextColor
import com.odroid.movieready.util.DummyDestinationsNavigator
import com.odroid.movieready.util.posterUrl
import com.odroid.movieready.view.layout.destinations.ItemDetailsScreenDestination
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VerticalItemWidget(
    navigator: DestinationsNavigator,
    tmdbItem: TmdbItem,
    exploreViewModel: ExploreViewModel
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(end = 12.dp, bottom = 8.dp, start = 12.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 12.dp,
        onClick = {
            navigator.navigate(ItemDetailsScreenDestination(movieId = tmdbItem.id))
        }
    ) {
        Box {
            Row(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tmdbItem.posterUrl.posterUrl())
                        .crossfade(true)
                        .error(R.drawable.app_icon_img)
                        .build(),
                    placeholder = painterResource(R.drawable.app_icon_img),
                    contentDescription = "contentDescription",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.35f)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        text = tmdbItem.title,
                        style = TextStyle(
                            fontFamily = fontBold,
                            fontSize = 16.sp,
                            color = primaryAppTextColor
                        )
                    )
                    Text(
                        text = tmdbItem.description,
                        style = TextStyle(
                            fontFamily = fontRegular,
                            fontSize = 12.sp,
                            color = primaryAppTextColor
                        ),
                        lineHeight = 14.sp,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = tmdbItem.avgRating.toString().plus("/10"),
                        style = TextStyle(
                            fontFamily = fontBold,
                            fontSize = 16.sp,
                            color = primaryAppTextColor
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewVerticalItem() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        VerticalItemWidget(
            DummyDestinationsNavigator(),
            TmdbItem(
                234L, "Humsafar", "",
                "", "", 7F, 8L
            ),
            ExploreViewModel()
        )
    }
}