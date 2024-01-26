package com.odroid.movieready.view.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.model.DumbCharadesSuggestionUiModel
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontRegular
import com.odroid.movieready.view.components.IshaaraFullScreenBottomSheetScaffold
import com.odroid.movieready.view.components.MovieLongTextSection
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet

@Preview
@Composable
fun PreviewMovieDetailsModal() {
    MovieDetailsModal(
        dumbCharadesSuggestionUiModel = DumbCharadesSuggestionUiModel.preview,
        onBackPressed = {}
    )
}

@Composable
fun MovieDetailsModal(
    dumbCharadesSuggestionUiModel: DumbCharadesSuggestionUiModel,
    onBackPressed: () -> Unit
) {
    BackHandler {
        onBackPressed.invoke()
    }

    val localConfiguration = LocalConfiguration.current

    val imageSize = remember {
        (localConfiguration.screenHeightDp * 0.60).dp
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (dumbCharadesSuggestionUiModel.posterPath.isNotEmpty()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(dumbCharadesSuggestionUiModel.posterPath)
                            .crossfade(true)
                            .error(R.drawable.app_icon_img)
                            .build(),
                        placeholder = painterResource(R.drawable.app_icon_img),
                        contentDescription = "contentDescription",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageSize)
                    )
                }
                AllMovieInfo(dumbCharadesSuggestionUiModel, modifier = Modifier
                    .fillMaxSize()
                    .padding(top = (imageSize - 40.dp)))
            }
        }
        Image(painter = painterResource(id = R.drawable.ic_close), contentDescription = "",
            modifier = Modifier.padding(12.dp).size(40.dp).align(Alignment.TopEnd).clickable {
                onBackPressed.invoke()
            })
    }
}

