package com.example.pazaramamovieapp.domain.model

data class MovieDetail(
    val genre: String,
    val imdbID: String,
    val ratingValue: Float,
    val imdbRating: String,
    val plot: String,
    val poster: String,
    val released: String,
    val runtime: String,
    val title: String,
)