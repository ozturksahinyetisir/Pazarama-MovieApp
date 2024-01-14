package com.example.pazaramamovieapp.presentation.home

import com.example.pazaramamovieapp.domain.model.Movie

data class HomeUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "Batman",
    val errorMessage: String? = null
)
