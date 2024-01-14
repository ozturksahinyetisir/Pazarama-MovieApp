package com.example.pazaramamovieapp.data.mapper

import com.example.pazaramamovieapp.data.dto.MovieDetailDto
import com.example.pazaramamovieapp.domain.model.MovieDetail
import com.example.pazaramamovieapp.util.UtilFunctions

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        genre = genre,
        imdbID = imdbID,
        imdbRating = imdbRating,
        ratingValue = UtilFunctions.convertImdbRatingToRatingBarValue(imdbRating = imdbRating),
        plot = plot,
        poster = poster,
        released = released,
        runtime = runtime,
        title = title
    )
}