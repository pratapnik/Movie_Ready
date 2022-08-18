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
import com.odroid.movieready.theming.*
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
    exploreViewModel: ExploreViewModel,
//    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(end = 16.dp, bottom = 12.dp, start = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 12.dp,
        onClick = {
//            onItemClick.invoke()
            navigator.navigate(ItemDetailsScreenDestination(movieId = tmdbItem.id))
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Card(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(0.35f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
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
                            .height(200.dp)
                            .fillMaxWidth()
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp)
                ) {
                    Text(
                        text = tmdbItem.title,
                        style = TextStyle(
                            fontFamily = fontBold,
                            fontSize = 16.sp,
                            color = primaryAppTextColor
                        ),
                        modifier = Modifier.padding(top = 8.dp)
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
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().fillMaxHeight()
                    ) {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            backgroundColor = ratingCardColor
                        ) {
                            Text(
                                text = tmdbItem.avgRating.toString(),
                                style = TextStyle(
                                    fontFamily = fontMedium,
                                    fontSize = 12.sp,
                                    color = primaryButtonTextColor,
                                    letterSpacing = 1.sp
                                ),
                                modifier = Modifier.padding(
                                    start = 8.dp,
                                    end = 8.dp,
                                    top = 4.dp,
                                    bottom = 4.dp
                                )
                            )
                        }
                        Text(
                            text = "release date: ${tmdbItem.releaseDate}",
                            style = TextStyle(
                                fontFamily = fontRegular,
                                fontSize = 12.sp,
                                color = grayColor
                            ),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
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
            ExploreViewModel(),
        )
    }
}