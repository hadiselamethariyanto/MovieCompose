package banyuwangi.digital.moviecompose.ui.screen.search

sealed class SearchEvent {
    data class OnSearchQueryChanged(val query: String) : SearchEvent()
}