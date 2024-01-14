package com.example.pazaramamovieapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.pazaramamovieapp.R

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .error(R.drawable.ic_movie)
        .into(this)
}