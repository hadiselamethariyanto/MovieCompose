package banyuwangi.digital.moviecompose.ui.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.usecase.search.SearchUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    var state by mutableStateOf(SearchState())

    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.query)
                if (state.searchQuery.isNotEmpty()){
                    searchJob?.cancel()
                    searchJob = viewModelScope.launch {
                        delay(500L)
                        searchMovie()
                    }
                }
            }
        }
    }

    private fun searchMovie(query: String = state.searchQuery.lowercase()) = viewModelScope.launch {
        searchUseCase.searchMovie(query).collect { res ->
            state = when (res) {
                is Resource.Loading -> {
                    state.copy(isLoading = true, error = null)
                }
                is Resource.Success -> {
                    val movies = res.data ?: arrayListOf()
                    state.copy(isLoading = false, error = null, movies = movies)
                }
                is Resource.Error -> {
                    state.copy(isLoading = false, error = res.message)
                }
            }
        }
    }
}