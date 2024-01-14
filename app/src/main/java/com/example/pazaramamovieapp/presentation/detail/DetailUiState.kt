package com.example.pazaramamovieapp.presentation.detail

import com.example.pazaramamovieapp.domain.model.MovieDetail

data class DetailUiState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val errorMessage: String? = null
)
