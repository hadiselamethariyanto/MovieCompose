package banyuwangi.digital.moviecompose.ui.screen.detail_tv

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.usecase.tv.TvUseCase
import banyuwangi.digital.moviecompose.ui.screen.detail_movie.DetailMovieState
import kotlinx.coroutines.launch

class DetailTvViewModel(private val tvUseCase: TvUseCase, savedStateHandle: SavedStateHandle) :
    ViewModel() {

    var state by mutableStateOf(DetailMovieState())

    init {
        savedStateHandle.get<Int>("id").let { id ->
            if (id == null) {
                return@let
            }

            viewModelScope.launch {
                tvUseCase.getDetailTv(id).collect { res ->
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

                tvUseCase.getSimilarTv(id).collect { res ->
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
            }
        }
    }

}