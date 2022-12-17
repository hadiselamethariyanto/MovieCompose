package banyuwangi.digital.moviecompose

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import banyuwangi.digital.moviecompose.ui.navigation.NavigationItem
import banyuwangi.digital.moviecompose.ui.navigation.Screen
import banyuwangi.digital.moviecompose.ui.screen.detail_collection.DetailCollectionScreen
import banyuwangi.digital.moviecompose.ui.screen.detail_movie.DetailMovieScreen
import banyuwangi.digital.moviecompose.ui.screen.detail_people.DetailPeopleScreen
import banyuwangi.digital.moviecompose.ui.screen.detail_tv.DetailTvScreen
import banyuwangi.digital.moviecompose.ui.screen.home.HomeScreen
import banyuwangi.digital.moviecompose.ui.screen.profile.ProfileScreen
import banyuwangi.digital.moviecompose.ui.screen.search.SearchScreen
import banyuwangi.digital.moviecompose.ui.screen.watchlist.WatchlistScreen
import banyuwangi.digital.moviecompose.ui.theme.MovieComposeTheme
import banyuwangi.digital.moviecompose.ui.theme.green500
import banyuwangi.digital.moviecompose.ui.theme.secondary2
import banyuwangi.digital.moviecompose.ui.theme.secondary3

@Composable
fun MovieComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToMovieDetail = { id ->
                        navController.navigate(
                            Screen.DetailMovie.createRoute(
                                id
                            )
                        )
                    },
                    navigateToTvDetail = { id ->
                        navController.navigate(Screen.DetailTv.createRoute(id))
                    },
                    navigateToPeopleDetail = { id ->
                        navController.navigate(Screen.DetailPeople.createRoute(id))
                    },
                    navigateToCollectionDetail = { id, name ->
                        navController.navigate(Screen.DetailCollection.createRoute(id, name))
                    }
                )
            }
            composable(Screen.Search.route) {
                SearchScreen(navigateToMovieDetail = { id ->
                    navController.navigate(
                        Screen.DetailSearch.createRoute(
                            id
                        )
                    )
                })
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Watchlist.route) {
                WatchlistScreen(navigateToMovieDetail = { id ->
                    navController.navigate(Screen.DetailWatchlist.createRoute(id))
                })
            }
            composable(
                route = Screen.DetailCollection.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType },
                    navArgument("name") { type = NavType.StringType })
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                val name = it.arguments?.getString("name") ?: ""
                DetailCollectionScreen(id = id, name = name)
            }
            composable(
                route = Screen.DetailMovie.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailMovieScreen(id = id)
            }
            composable(
                route = Screen.DetailTv.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailTvScreen(id = id)
            }

            composable(
                route = Screen.DetailSearch.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailMovieScreen(id = id)
            }

            composable(
                route = Screen.DetailWatchlist.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailMovieScreen(id = id)
            }

            composable(
                route = Screen.DetailPeople.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailPeopleScreen(id)
            }
        }
    }

}

@Composable
private fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_search),
                icon = Icons.Default.Search,
                screen = Screen.Search
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_watch_list),
                icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_bookmark_border_24),
                screen = Screen.Watchlist
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            )
        )

        BottomNavigation(backgroundColor = secondary2) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    selectedContentColor = green500,
                    unselectedContentColor = secondary3,
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieComposeAppPreview() {
    MovieComposeTheme {
        MovieComposeApp()
    }
}