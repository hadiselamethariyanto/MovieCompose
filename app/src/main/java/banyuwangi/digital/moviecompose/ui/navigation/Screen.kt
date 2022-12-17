package banyuwangi.digital.moviecompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Watchlist : Screen("watchlist")
    object Profile : Screen("profile")
    object DetailMovie : Screen("home/movie/{id}") {
        fun createRoute(id: Int) = "home/movie/$id"
    }

    object DetailTv : Screen("home/tv/{id}") {
        fun createRoute(id: Int) = "home/tv/$id"
    }

    object DetailPeople : Screen("home/people/{id}") {
        fun createRoute(id: Int) = "home/people/$id"
    }

    object DetailSearch : Screen("search/{id}") {
        fun createRoute(id: Int) = "search/$id"
    }

    object DetailWatchlist : Screen("watchlist/{id}") {
        fun createRoute(id: Int) = "watchlist/$id"
    }

    object DetailCollection : Screen("collection/{id}/{name}") {
        fun createRoute(id: Int, name: String) = "collection/$id/$name"
    }


}