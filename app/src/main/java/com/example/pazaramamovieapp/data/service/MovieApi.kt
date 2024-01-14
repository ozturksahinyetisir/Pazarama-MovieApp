package com.example.pazaramamovieapp.data.service

import com.example.pazaramamovieapp.data.dto.MovieDetailDto
import com.example.pazaramamovieapp.domain.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchQuery: String
    ): Response<MovieResponse>

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbID: String
    ): Response<MovieDetailDto>
}