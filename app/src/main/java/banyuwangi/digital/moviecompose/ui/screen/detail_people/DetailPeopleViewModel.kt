package banyuwangi.digital.moviecompose.ui.screen.detail_people

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.usecase.people.PeopleUseCase
import kotlinx.coroutines.launch

class DetailPeopleViewModel(
    private val peopleUseCase: PeopleUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(DetailPeopleState())

    init {
        savedStateHandle.get<Int>("id").let { id ->
            if (id == null) {
                return@let
            }

            viewModelScope.launch {
                peopleUseCase.getDetailPeople(id).collect { res ->
                    state = when (res) {
                        is Resource.Loading -> {
                            state.copy(isLoading = true, error = null)
                        }
                        is Resource.Success -> {
                            state.copy(isLoading = false, detail = res.data, error = null)
                        }
                        is Resource.Error -> {
                            state.copy(isLoading = false, error = null)
                        }
                    }
                }

                peopleUseCase.getMovieCredits(id).collect{res->
                    state = when(res){
                        is Resource.Loading->{
                            state.copy(isLoading = false, error = null)
                        }
                        is Resource.Success->{
                            val filmography = res.data?: arrayListOf()
                            state.copy(filmography = filmography, isLoading = false, error = null)
                        }
                        is Resource.Error->{
                            state.copy(isLoading = false, error = res.message)
                        }
                    }
                }
            }
        }
    }
}