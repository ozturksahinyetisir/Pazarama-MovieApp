package com.example.pazaramamovieapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.pazaramamovieapp.R
import com.example.pazaramamovieapp.databinding.FragmentDetailBinding
import com.example.pazaramamovieapp.domain.model.MovieDetail
import com.example.pazaramamovieapp.util.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUiState()
        binding.errorView.btnRetry.setOnClickListener {
            viewModel.retry()
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.progressBar.isVisible = uiState.isLoading
                    uiState.errorMessage?.let {
                        binding.errorView.root.isVisible = true
                        binding.errorView.txtError.text = it
                    }
                    uiState.movieDetail?.let {
                        binding.errorView.root.isVisible = false
                        bindMovieDetail(it)
                    }
                }
            }
        }
    }

    private fun bindMovieDetail(movieDetail: MovieDetail) {
        binding.imvPoster.loadImage(movieDetail.poster)
        binding.txtMovieName.text = movieDetail.title
        binding.txtRuntime.text = movieDetail.runtime
        binding.txtGenre.text = movieDetail.genre
        binding.txtRelease.text = movieDetail.released
        binding.txtPlot.text = movieDetail.plot
        binding.ratingBar.rating = movieDetail.ratingValue
        binding.txtRate.text = requireContext().getString(
            R.string.rating_imdb,
            movieDetail.imdbRating,
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}