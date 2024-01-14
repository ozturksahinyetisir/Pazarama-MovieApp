package com.example.pazaramamovieapp.presentation.home

import app.cash.turbine.test
import com.example.pazaramamovieapp.data.repository.FakeMovieRepository
import com.example.pazaramamovieapp.domain.usecase.GetMoviesUseCase
import com.example.pazaramamovieapp.rules.MainDispatcherRule
import com.example.pazaramamovieapp.util.dispatcher.DispatcherProvider
import com.example.pazaramamovieapp.util.dispatcher.TestDefaultDispatcher
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var movieRepository: FakeMovieRepository
    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDefaultDispatcher()
        movieRepository = FakeMovieRepository()
        viewModel = HomeViewModel(
            getMoviesUseCase = GetMoviesUseCase(movieRepository),
            dispatcherProvider = dispatcherProvider
        )
    }

    @Test
    fun whenViewModelInit_getMoviesReturnSuccess_shouldReturnListOfMovies() = runTest {
        viewModel.uiState.test {
            val uiState = awaitItem()
            assertThat(uiState.isLoading).isTrue()
            advanceUntilIdle()
            val uiState2 = awaitItem()
            assertThat(uiState2.isLoading).isFalse()
            assertThat(uiState2.movies).isNotEmpty()
            assertThat(uiState2.errorMessage).isNull()
        }
    }

    @Test
    fun whenViewModelInit_getMoviesReturnFalse_shouldReturnListOfMovies() = runTest {
        movieRepository.isReturnNetworkError = true
        viewModel.uiState.test {
            val uiState = awaitItem()
            assertThat(uiState.isLoading).isTrue()
            advanceUntilIdle()
            val uiState2 = awaitItem()
            assertThat(uiState2.isLoading).isFalse()
            assertThat(uiState2.errorMessage).isNotNull()
        }
    }
}