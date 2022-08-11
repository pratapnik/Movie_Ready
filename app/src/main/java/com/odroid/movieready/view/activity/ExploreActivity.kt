package com.odroid.movieready.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.material.composethemeadapter.MdcTheme
import com.odroid.movieready.R
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.view.layout.*
import com.odroid.movieready.view.layout.destinations.Destination
import com.odroid.movieready.view.layout.destinations.ExploreScreenDestination
import com.odroid.movieready.view.layout.destinations.SavedItemsScreenDestination
import com.odroid.movieready.view_intent.BottomNavItem
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.*
import com.ramcosta.composedestinations.utils.isRouteOnBackStack

class ExploreActivity : ComponentActivity() {

    private val exploreViewModel: ExploreViewModel by viewModels()
    private val movieClicked = mutableStateOf(MovieResponse())
    private lateinit var bottomBarState: MutableState<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MdcTheme {
                ScaffoldWithBottomMenu()
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
    @Composable
    fun ScaffoldWithBottomMenu() {
        val navController = rememberAnimatedNavController()
        bottomBarState = rememberSaveable { (mutableStateOf(true)) }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomBar(navController, bottomBarState) },
            backgroundColor = Color.White
        ) {
            Box(modifier = Modifier.padding(it)) {
                DestinationsNavHost(navController = navController, navGraph = NavGraphs.root,
                    dependenciesContainerBuilder = {
                        dependency(exploreViewModel)
                    })
            }
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun BottomBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
        val items = listOf(
            BottomNavItem.Movies,
            BottomNavItem.TvShows,
            BottomNavItem.Saved
        )
        val currentDestination: Destination =
            navController.appCurrentDestinationAsState().value
                ?: NavGraphs.root.startAppDestination
        when(currentDestination) {
            ExploreScreenDestination,
            SavedItemsScreenDestination ->
                AnimatedVisibility(
                    visible = bottomBarState.value,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                    content = {
                        BottomNavigation(
                            elevation = 10.dp,
                            backgroundColor = colorResource(id = R.color.bottom_nav_color),
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 0.dp)
                                .clip(RoundedCornerShape(12.dp))

                        ) {


                            items.forEach { destination ->
                                val isCurrentDestOnBackStack =
                                    navController.isRouteOnBackStack(destination.direction)
                                BottomNavigationItem(
                                    selected = currentDestination == destination.direction,
                                    onClick = {
                                        if (isCurrentDestOnBackStack) {
                                            navController.popBackStack(destination.direction, false)
                                            return@BottomNavigationItem
                                        }
                                        navController.navigate(destination.direction) {
                                            popUpTo(NavGraphs.root) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Image(
                                            painter = painterResource(id = destination.icon),
                                            contentDescription = destination.title,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    },
                                    label = { BottomNavigationItemText(destination.title) },
                                )
                            }
                        }
                    }
                )
            else -> {}
        }
    }

    @Composable
    fun BottomNavigationItemText(text: String) {
        Text(
            text = text, modifier = Modifier.padding(top = 4.dp), style = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.font_bold))
            )
        )
    }
}