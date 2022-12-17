package banyuwangi.digital.moviecompose.ui.screen.detail_movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.usecase.movie.MoviesUseCase
import banyuwangi.digital.core.domain.usecase.watchlist.WatchlistUseCase
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val moviesUseCase: MoviesUseCase,
    private val watchlistUseCase: WatchlistUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(DetailMovieState())

    init {
        savedStateHandle.get<Int>("id").let { id ->
            if (id == null) {
                return@let
            }

            viewModelScope.launch {
                moviesUseCase.getDetailMovie(id).collect { res ->
                    when (res) {
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true,
                                detailMovie = null,
                                error = null
                            )
                        }
                        is Resource.Success -> {
                            state = state.copy(
                                isLoading = false,
                                detailMovie = res.data,
                                error = null
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                isLoading = false,
                                detailMovie = null,
                                error = res.message
                            )
                        }
                    }
                }

                moviesUseCase.getSimilarMovie(id).collect { res ->
                    when (res) {
                        is Resource.Loading -> {
                            state = state.copy(isLoading = true, error = null)
                        }
                        is Resource.Success -> {
                            state = state.copy(
                                isLoading = false,
                                similarMovies = res.data ?: emptyList(),
                                error = null
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                isLoading = false,
                                detailMovie = null,
                                error = res.message
                            )
                        }
                    }
                }

                watchlistUseCase.isMovieWatchlist(id).collect {
                    state = state.copy(isMovieWatchlist = it)
                }
            }
        }
    }

    fun insertMovie() = viewModelScope.launch {
        val detailMovieDomain = state.detailMovie
        detailMovieDomain?.let { watchlistUseCase.setWatchlistMovie(it) }
    }

}