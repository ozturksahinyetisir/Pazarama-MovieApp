package com.example.pazaramamovieapp.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pazaramamovieapp.domain.usecase.GetMovieDetailUseCase
import com.example.pazaramamovieapp.util.NavArgs
import com.example.pazaramamovieapp.util.Resource
import com.example.pazaramamovieapp.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val imdbIDState = MutableStateFlow<String?>(null)
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update { it.copy(isLoading = false, errorMessage = throwable.message) }
    }

    init {
        val imdbId = savedStateHandle.get<String>(NavArgs.imdbID)
        imdbIDState.update { imdbId }
        imdbId?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(dispatcherProvider.IO + exceptionHandler) {
            when (val resource = getMovieDetailUseCase(imdbId)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            movieDetail = resource.data
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = resource.errorMessage ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }

    fun retry() {
        imdbIDState.value?.let {
            getMovieDetail(it)
        }
    }
}