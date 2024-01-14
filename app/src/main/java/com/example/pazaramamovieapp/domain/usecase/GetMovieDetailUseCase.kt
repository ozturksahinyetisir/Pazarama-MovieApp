package com.example.pazaramamovieapp.domain.usecase

import com.example.pazaramamovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(imdbID: String) = movieRepository.getMovieDetail(imdbID)
}