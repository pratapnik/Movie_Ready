package com.odroid.movieready.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
import com.odroid.movieready.entity.SourceType
import com.odroid.movieready.theming.primaryAppTextColor
import com.odroid.movieready.theming.primaryButtonColor
import com.odroid.movieready.theming.primaryDarkModeAppTextColor
import com.odroid.movieready.view.layout.NavGraphs
import com.odroid.movieready.view.layout.appCurrentDestinationAsState
import com.odroid.movieready.view.layout.destinations.*
import com.odroid.movieready.view.layout.startAppDestination
import com.odroid.movieready.view_intent.BottomNavItem
import com.odroid.movieready.view_intent.EntertainmentCategory
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.utils.isRouteOnBackStack

class ExploreActivity : ComponentActivity() {

    private val exploreViewModel: ExploreViewModel by viewModels()

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
        val currentDestination: Destination =
            navController.appCurrentDestinationAsState().value
                ?: NavGraphs.root.startAppDestination
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomBar(navController) },
            backgroundColor = Color.White,
            topBar = {
                when(currentDestination) {
                    PopularMoviesScreenDestination,
                    TopRatedMoviesScreenDestination,
                    NowPlayingMoviesScreenDestination,
                    UpcomingMoviesScreenDestination -> {
                        TopAppBar {
                            Text(
                                text = "movies",
                                modifier = Modifier.padding(start = 16.dp, top = 6.dp),
                                style = TextStyle(
                                    color = primaryDarkModeAppTextColor,
                                    fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.font_bold))
                                )
                            )
                        }
                    }
                    else -> {}
                }
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                DestinationsNavHost(navController = navController, navGraph = NavGraphs.root,
                    dependenciesContainerBuilder = {
                        dependency(exploreViewModel)
                    })
            }
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun BottomBar(navController: NavController) {
        val bottomNavItems = exploreViewModel.getMoviesCategories()
        val currentDestination: Destination =
            navController.appCurrentDestinationAsState().value
                ?: NavGraphs.root.startAppDestination
        when (currentDestination) {
            PopularMoviesScreenDestination,
            TopRatedMoviesScreenDestination,
            NowPlayingMoviesScreenDestination,
            UpcomingMoviesScreenDestination ->
                BottomNavigation(
                    elevation = 10.dp,
                    backgroundColor = colorResource(id = R.color.bottom_nav_color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    bottomNavItems.forEach { destination ->
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
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            label = { BottomNavigationItemText(destination.title) },
                        )
                    }
                }
            else -> {}
        }
    }

    @Composable
    fun BottomNavigationItemText(text: String) {
        Text(
            text = text, modifier = Modifier.padding(top = 2.dp), style = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.font_bold))
            )
        )
    }
}