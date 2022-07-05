package com.odroid.movieready.view.layout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.view_intent.CategoryWithList
import com.odroid.movieready.view_model.ExploreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewExploreScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        ExploreScreen(getCategoriesWithList(), ExploreViewModel())
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExploreScreen(
    categoriesWithList: List<CategoryWithList>,
    exploreViewModel: ExploreViewModel
) {
    // Declaring a Boolean value to
    // store bottom sheet collapsed state
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState =
        BottomSheetState(BottomSheetValue.Collapsed)
    )

    // Declaing Coroutine scope
    val coroutineScope = rememberCoroutineScope()
    
    var movieName = remember { mutableStateOf("movie")}

    // Creating a Bottom Sheet
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(colorResource(R.color.primary_button_color))
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = movieName.value, style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.font_bold)),
                            fontSize = 20.sp,
                            color = colorResource(R.color.primary_text_color)
                        )
                    )
                }
            }
        },
        sheetPeekHeight = 0.dp
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
        ) {
            items(categoriesWithList) { list: CategoryWithList ->
                MoviesListWidget(
                    categoryWithList = list, exploreViewModel,
                    bottomSheetScaffoldState, coroutineScope, movieName
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun MoviesListWidget(
    categoryWithList: CategoryWithList,
    exploreViewModel: ExploreViewModel,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
    movieName: MutableState<String>
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
                .padding(start = 4.dp),
            painter = painterResource(id = categoryWithList.icon),
            contentDescription = "contentDescription"
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = categoryWithList.title,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.font_bold)),
                fontSize = 20.sp,
                color = colorResource(R.color.primary_text_color)
            )
        )
    }
    val moviesList = categoryWithList.list
    if (categoryWithList is CategoryWithList.TopRated) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(8.dp),
            cells = GridCells.Fixed(3),
            modifier = Modifier
                .height(500.dp)
                .padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            items(moviesList) { movie: MovieResponse ->
                MovieWidget(
                    movieResponse = movie,
                    exploreViewModel,
                    bottomSheetScaffoldState, coroutineScope, movieName
                )
            }
        }
    } else {
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            items(moviesList) { movie: MovieResponse ->
                MovieWidget(
                    movieResponse = movie,
                    exploreViewModel,
                    bottomSheetScaffoldState,
                    coroutineScope,
                    movieName
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieWidget(
    movieResponse: MovieResponse,
    exploreViewModel: ExploreViewModel,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
    movieName: MutableState<String>
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(150.dp)
            .padding(end = 4.dp, bottom = 4.dp)
            .border(2.dp, Color.DarkGray, RoundedCornerShape(8.dp))
            .clickable {
                coroutineScope.launch {
                    movieName.value = movieResponse.title
                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieResponse.posterUrl)
                    .crossfade(true)
                    .error(R.drawable.app_icon_img)
                    .build(),
                placeholder = painterResource(R.drawable.app_icon_img),
                contentDescription = "contentDescription",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ), startY = 100f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = movieResponse.title,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.font_bold)),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}


fun getCategoriesWithList(): List<CategoryWithList> {
    return listOf(
        CategoryWithList.Trending,
        CategoryWithList.NowPlaying,
        CategoryWithList.TopRated
    )
}
