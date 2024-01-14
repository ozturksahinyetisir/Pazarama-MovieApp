package com.example.pazaramamovieapp.presentation.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.pazaramamovieapp.data.repository.FakeMovieRepository
import com.example.pazaramamovieapp.domain.usecase.GetMovieDetailUseCase
import com.example.pazaramamovieapp.rules.MainDispatcherRule
import com.example.pazaramamovieapp.util.NavArgs
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
class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: DetailViewModel
    private lateinit var movieRepository: FakeMovieRepository
    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        movieRepository = FakeMovieRepository()
        dispatcherProvider = TestDefaultDispatcher()
        val savedStateHandle = SavedStateHandle(
            mapOf(
                NavArgs.imdbID to "tt0372784"
            )
        )
        viewModel = DetailViewModel(
            getMovieDetailUseCase = GetMovieDetailUseCase(movieRepository),
            savedStateHandle = savedStateHandle,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Test
    fun whenViewModelInit_getMoviesDetailReturnSuccess_updateState() = runTest {
        viewModel.uiState.test {
            val uiState = awaitItem()
            assertThat(uiState.isLoading).isTrue()
            advanceUntilIdle()
            val uiState2 = awaitItem()
            assertThat(uiState2.isLoading).isFalse()
            assertThat(uiState2.errorMessage).isNull()
            assertThat(uiState2.movieDetail).isNotNull()
        }
    }

    @Test
    fun whenViewModelInit_getMoviesDetailReturnFalse_updateState() = runTest {
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