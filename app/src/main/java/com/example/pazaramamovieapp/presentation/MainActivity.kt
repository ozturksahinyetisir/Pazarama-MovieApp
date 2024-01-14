package com.example.pazaramamovieapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pazaramamovieapp.databinding.ActivityMainBinding
import com.example.pazaramamovieapp.domain.repository.MovieRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var repository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}