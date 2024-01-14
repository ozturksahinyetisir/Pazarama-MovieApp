package com.example.pazaramamovieapp.util

object UtilFunctions {
    fun convertImdbRatingToRatingBarValue(imdbRating: String): Float {
        imdbRating.toFloatOrNull()?.let {
            return it / 2
        } ?: return 0f
    }
}