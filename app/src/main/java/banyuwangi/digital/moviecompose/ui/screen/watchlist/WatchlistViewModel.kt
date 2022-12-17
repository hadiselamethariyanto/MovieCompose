package banyuwangi.digital.moviecompose.ui.screen.watchlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banyuwangi.digital.core.domain.usecase.watchlist.WatchlistUseCase
import kotlinx.coroutines.launch

class WatchlistViewModel(private val watchlistUseCase: WatchlistUseCase) : ViewModel() {

    var state by mutableStateOf(WatchlistState())

    init {
        viewModelScope.launch {
            watchlistUseCase.getWatchlist().collect {
                state = state.copy(watchlist = it)
            }
        }
    }
}