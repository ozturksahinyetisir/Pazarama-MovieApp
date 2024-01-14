package com.example.pazaramamovieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pazaramamovieapp.domain.usecase.GetMoviesUseCase
import com.example.pazaramamovieapp.util.Resource
import com.example.pazaramamovieapp.util.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    private var searchJob: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update { it.copy(isLoading = false, errorMessage = throwable.message) }
    }

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch(dispatcherProvider.IO + exceptionHandler) {
            _uiState.update { it.copy(isLoading = true) }
            when (val response = getMoviesUseCase(uiState.value.searchQuery)) {
                is Resource.Success -> {
                    response.data?.let {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                movies = response.data,
                                errorMessage = null
                            )
                        }
                    } ?: kotlin.run {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = "No movies found"
                            )
                        }
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = response.errorMessage ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }

    fun retry() {
        _uiState.update { it.copy(errorMessage = null) }
        getMovies()
    }

    fun setQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch(dispatcherProvider.IO + exceptionHandler) {
            delay(300)
            getMovies()
        }
    }
}