package com.example.pazaramamovieapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pazaramamovieapp.databinding.MovieItemBinding
import com.example.pazaramamovieapp.domain.model.Movie
import com.example.pazaramamovieapp.util.loadImage

class MovieAdapter(
    private val listener: Listener
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffUtil()) {

    interface Listener {
        fun onItemClick(movie: Movie)
    }

    inner class MovieViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.root.setOnClickListener {
                listener.onItemClick(movie)
            }
            binding.imvPoster.loadImage(movie.poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.bind(movieItem)
    }
}

private class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.imdbID == newItem.imdbID
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}