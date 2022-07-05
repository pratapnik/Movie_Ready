package com.odroid.movieready.view.activity

import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
import com.odroid.movieready.R
import com.odroid.movieready.base.BaseComposeActivity
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.view.layout.ExploreScreen
import com.odroid.movieready.view.layout.FavouriteScreen
import com.odroid.movieready.view.layout.getCategoriesWithList
import com.odroid.movieready.view_intent.BottomNavItem
import com.odroid.movieready.view_intent.getMoviesList
import com.odroid.movieready.view_model.ExploreViewModel

class ExploreActivity : BaseComposeActivity() {

    private val exploreViewModel: ExploreViewModel by viewModels()
    private val movieClicked = mutableStateOf(MovieResponse())

    @Composable
    override fun Content() {
        ScaffoldWithBottomMenu()
    }

    @Composable
    fun ScaffoldWithBottomMenu() {
        val navController = rememberNavController()
        Scaffold(bottomBar = { BottomBar(navController) }
        ) {
            NavigationGraph(navController = navController)
        }
    }

    @Composable
    fun BottomBar(navController: NavController) {
        val items = listOf(
            BottomNavItem.Explore,
            BottomNavItem.Favourite
        )
        BottomNavigation(
            elevation = 10.dp, backgroundColor = colorResource(id = R.color.primary_button_color)
        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach {
                BottomNavigationItem(icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        "",
                        tint = colorResource(id = R.color.primary_text_color)
                    )
                },
                    label = { BottomNavigationItemText(text = it.title) },
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
        NavHost(navController, startDestination = BottomNavItem.Explore.screen_route) {
            composable(BottomNavItem.Explore.screen_route) {
                ExploreScreen(getCategoriesWithList(), exploreViewModel)
            }
            composable(BottomNavItem.Favourite.screen_route) {
                FavouriteScreen(getMoviesList(), exploreViewModel)
            }
        }
    }

    @Composable
    fun BottomNavigationItemText(text: String) {
        Text(
            text = text, modifier = Modifier.padding(top = 4.dp), style = TextStyle(
                color = colorResource(R.color.primary_text_color),
                fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.font_regular))
            )
        )
    }
}