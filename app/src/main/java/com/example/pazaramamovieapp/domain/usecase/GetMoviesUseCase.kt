package com.example.pazaramamovieapp.domain.usecase

import com.example.pazaramamovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(searchQuery: String) = movieRepository.getMovies(searchQuery)
}