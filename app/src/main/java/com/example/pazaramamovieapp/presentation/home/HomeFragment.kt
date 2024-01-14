package com.example.pazaramamovieapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.pazaramamovieapp.databinding.FragmentHomeBinding
import com.example.pazaramamovieapp.domain.model.Movie
import com.example.pazaramamovieapp.presentation.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), MovieAdapter.Listener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(this)
        binding.rvMovies.adapter = movieAdapter
        addTextChangedListener()
        collectUiState()
        binding.errorView.btnRetry.setOnClickListener {
            viewModel.retry()
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
                    } ?: kotlin.run {
                        binding.errorView.root.isVisible = false
                    }
                    movieAdapter.submitList(uiState.movies)
                }
            }
        }
    }

    private fun addTextChangedListener() {
        binding.editText.addTextChangedListener {
            viewModel.setQuery(it.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie.imdbID)
        findNavController().navigate(action)
    }
}