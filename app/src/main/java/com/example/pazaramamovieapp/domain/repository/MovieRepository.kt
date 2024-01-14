package com.example.pazaramamovieapp.domain.repository

import com.example.pazaramamovieapp.domain.model.Movie
import com.example.pazaramamovieapp.domain.model.MovieDetail
import com.example.pazaramamovieapp.util.Resource

interface MovieRepository {
    suspend fun getMovies(searchQuery: String): Resource<List<Movie>>

    suspend fun getMovieDetail(imdbID: String): Resource<MovieDetail>
}