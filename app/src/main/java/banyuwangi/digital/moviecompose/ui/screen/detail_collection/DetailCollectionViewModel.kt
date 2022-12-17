package banyuwangi.digital.moviecompose.ui.screen.detail_collection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banyuwangi.digital.core.data.common.Resource
import banyuwangi.digital.core.domain.usecase.collections.CollectionUseCase
import kotlinx.coroutines.launch

class DetailCollectionViewModel(
    private val collectionUseCase: CollectionUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(DetailCollectionState())

    init {
        savedStateHandle.get<Int>("id").let { id ->
            if (id == null) {
                return@let
            }

            viewModelScope.launch {
                collectionUseCase.getCollectionDetail(id).collect { res ->
                    state = when (res) {
                        is Resource.Loading -> {
                            state.copy(isLoading = true, error = null)
                        }
                        is Resource.Success -> {
                            state.copy(
                                isLoading = false,
                                error = null,
                                movies = res.data ?: arrayListOf()
                            )
                        }
                        is Resource.Error -> {
                            state.copy(isLoading = false, error = res.message)
                        }
                    }
                }
            }
        }
    }

}