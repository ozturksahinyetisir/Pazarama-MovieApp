package com.example.pazaramamovieapp.data.repository

import android.util.Log
import com.example.pazaramamovieapp.data.mapper.toMovieDetail
import com.example.pazaramamovieapp.data.service.MovieApi
import com.example.pazaramamovieapp.domain.model.Movie
import com.example.pazaramamovieapp.domain.model.MovieDetail
import com.example.pazaramamovieapp.domain.repository.MovieRepository
import com.example.pazaramamovieapp.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getMovies(searchQuery: String): Resource<List<Movie>> {
        return try {
            val response = movieApi.getMovies(searchQuery)
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    Log.d("MovieRepositoryImpl", "getMovies: ${resultResponse.search}")
                    Resource.Success(resultResponse.search)
                } ?: Resource.Error("An unknown error occurred", null)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getMovieDetail(imdbID: String): Resource<MovieDetail> {
        return try {
            val response = movieApi.getMovieDetail(imdbID)
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    Resource.Success(resultResponse.toMovieDetail())
                } ?: Resource.Error("An unknown error occurred", null)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}