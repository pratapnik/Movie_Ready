package com.odroid.movieready.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.material.composethemeadapter.MdcTheme
import com.odroid.movieready.R
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.view.layout.*
import com.odroid.movieready.view.layout.destinations.Destination
import com.odroid.movieready.view_intent.BottomNavItem
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate

class ExploreActivity : ComponentActivity() {

    private val exploreViewModel: ExploreViewModel by viewModels()
    private val movieClicked = mutableStateOf(MovieResponse())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MdcTheme {
                ScaffoldWithBottomMenu()
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun ScaffoldWithBottomMenu() {
        val navController = rememberAnimatedNavController()
        Scaffold(
            bottomBar = { BottomBar(navController) },
            backgroundColor = Color.White
        ) {
            Box(modifier = Modifier.padding(it)) {
                DestinationsNavHost(navController = navController, navGraph = NavGraphs.root,
                dependenciesContainerBuilder =  {
                    dependency(exploreViewModel)
                })
            }
        }
    }

    @Composable
    fun BottomBar(navController: NavController) {
        val items = listOf(
            BottomNavItem.Movies,
            BottomNavItem.TvShows,
            BottomNavItem.Saved
        )
        BottomNavigation(
            elevation = 10.dp, backgroundColor = colorResource(id = R.color.bottom_nav_color),
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 0.dp)
                .clip(RoundedCornerShape(12.dp))

        ) {
            val currentDestination: Destination = navController.appCurrentDestinationAsState().value
                    ?: NavGraphs.root.startAppDestination

            BottomNavigation {
                items.forEach { destination ->
                    BottomNavigationItem(
                        selected = currentDestination == destination.direction,
                        onClick = {
                            navController.navigate(destination.direction) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Image(
                                painter = painterResource(id = destination.icon),
                                contentDescription = destination.title
                            )
                        },
                        label = { BottomNavigationItemText(destination.title) },
                    )
                }
            }

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