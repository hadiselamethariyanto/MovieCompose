package banyuwangi.digital.moviecompose.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.usecase.collections.CollectionUseCase
import banyuwangi.digital.core.domain.usecase.movie.MoviesUseCase
import banyuwangi.digital.core.domain.usecase.people.PeopleUseCase
import banyuwangi.digital.core.domain.usecase.tv.TvUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesUseCase: MoviesUseCase,
    private val tvUseCase: TvUseCase,
    private val collectionUseCase: CollectionUseCase,
    private val peopleUseCase: PeopleUseCase
) :
    ViewModel() {

    var state by mutableStateOf(HomeState())

    init {
        viewModelScope.launch {
            moviesUseCase.getPopularMovie().collect { res ->
                state = when (res) {
                    is Resource.Loading -> {
                        state.copy(isLoading = true, error = null)
                    }
                    is Resource.Success -> {
                        val popularMovies = res.data ?: arrayListOf()
                        state.copy(popularMovies = popularMovies, isLoading = false, error = null)
                    }
                    is Resource.Error -> {
                        state.copy(isLoading = false, error = res.message)
                    }
                }
            }

            tvUseCase.getPopularTv().collect { res ->
                state = when (res) {
                    is Resource.Loading -> {
                        state.copy(isLoading = false, error = null)
                    }
                    is Resource.Success -> {
                        val popularTv = res.data ?: emptyList()
                        state.copy(popularTv = popularTv, isLoading = false, error = null)
                    }
                    is Resource.Error -> {
                        state.copy(isLoading = false, error = res.message)
                    }
                }
            }

            collectionUseCase.getCollections().collect { res ->
                state = when (res) {
                    is Resource.Loading -> {
                        state.copy(isLoading = false, error = null)
                    }
                    is Resource.Success -> {
                        val collections = res.data ?: emptyList()
                        state.copy(isLoading = false, error = null, collections = collections)
                    }
                    is Resource.Error -> {
                        state.copy(isLoading = false, error = res.message)
                    }
                }
            }

            peopleUseCase.getPopularPeople().collect { res ->
                state = when (res) {
                    is Resource.Loading -> {
                        state.copy(isLoading = false, error = null)
                    }
                    is Resource.Success -> {
                        val peoples = res.data ?: arrayListOf()
                        state.copy(popularPeople = peoples, isLoading = false, error = null)
                    }
                    is Resource.Error -> {
                        state.copy(isLoading = false, error = null)
                    }
                }
            }
        }
    }


}