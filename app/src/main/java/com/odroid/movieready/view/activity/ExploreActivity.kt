package com.odroid.movieready.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.android.material.composethemeadapter.MdcTheme
import com.odroid.movieready.R
import com.odroid.movieready.theming.bottomNavColor
import com.odroid.movieready.theming.primaryAppTextColor
import com.odroid.movieready.view.NavGraphs
import com.odroid.movieready.view.appCurrentDestinationAsState
import com.odroid.movieready.view.destinations.NowPlayingMoviesScreenDestination
import com.odroid.movieready.view.destinations.PopularMoviesScreenDestination
import com.odroid.movieready.view.destinations.TopRatedMoviesScreenDestination
import com.odroid.movieready.view.destinations.UpcomingMoviesScreenDestination
import com.odroid.movieready.view.startAppDestination
import com.odroid.movieready.view.views.AppRouter
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    @OptIn(
        ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class,
        ExperimentalMaterialNavigationApi::class, ExperimentalMaterialApi::class
    )
    @Composable
    fun ScaffoldWithBottomMenu() {
        val bottomSheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            animationSpec = TweenSpec(durationMillis = 500),
            skipHalfExpanded = true
        )
        // BottomSheetNavigator to help navigate to bottom sheets
        val bottomSheetNavigator =
            remember { BottomSheetNavigator(sheetState = bottomSheetState) }
        // Animated nav controller is required for bottom sheet navigator to work with compose destinations
        val navController = rememberAnimatedNavController(bottomSheetNavigator)
        // Animated navHost engine is required for bottom sheet navigator to work with compose destinations
        val navHostEngine = rememberAnimatedNavHostEngine()

        navController.navigatorProvider += bottomSheetNavigator

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomBar(navController) },
            backgroundColor = Color.White,
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = NavGraphs.exploreGraph,
                dependenciesContainerBuilder = {
                    dependency(AppRouter(navController))
                    dependency(bottomSheetState)
                    dependency(exploreViewModel)
                }, engine = navHostEngine
            )
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun BottomBar(navController: NavController) {
        val bottomNavItems = exploreViewModel.getMoviesCategories()
        val currentDestination =
            navController.appCurrentDestinationAsState().value
                ?: NavGraphs.root.startAppDestination
        when (currentDestination) {
            PopularMoviesScreenDestination,
            TopRatedMoviesScreenDestination,
            NowPlayingMoviesScreenDestination,
            UpcomingMoviesScreenDestination ->
                BottomNavigation(
                    elevation = 10.dp,
                    backgroundColor = bottomNavColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
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
            text = text, style = TextStyle(
                color = primaryAppTextColor,
                fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.font_bold))
            )
        )
    }
}