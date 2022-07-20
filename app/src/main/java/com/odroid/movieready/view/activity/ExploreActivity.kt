package com.odroid.movieready.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.google.android.material.composethemeadapter.MdcTheme
import com.odroid.movieready.R
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.view.layout.ExploreScreen
import com.odroid.movieready.view.layout.FavouriteScreen
import com.odroid.movieready.view.layout.getCategoriesWithList
import com.odroid.movieready.view_intent.BottomNavItem
import com.odroid.movieready.view_model.ExploreViewModel

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

    @Composable
    fun ScaffoldWithBottomMenu() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomBar(navController) },
            backgroundColor = Color.White
        ) {
            Box(modifier = Modifier.padding(it)) {
                NavigationGraph(navController = navController)
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
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach {
                BottomNavigationItem(icon = {
                    Image(
                        painter = painterResource(id = it.icon),
                        "",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp)
                    )
                },
                    label = {
                        BottomNavigationItemText(text = it.title)
                    },
                    selected = currentRoute == it.screen_route,
                    onClick = {
                        navController.navigate(it.screen_route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.Movies.screen_route) {
            composable(BottomNavItem.Movies.screen_route) {
                ExploreScreen(getCategoriesWithList(), exploreViewModel)
            }
            composable(BottomNavItem.Saved.screen_route) {
                FavouriteScreen(exploreViewModel)
            }
            composable(BottomNavItem.TvShows.screen_route) {
                ExploreScreen(getCategoriesWithList(), exploreViewModel)
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