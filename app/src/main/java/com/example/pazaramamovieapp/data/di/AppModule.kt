package com.example.pazaramamovieapp.data.di

import com.example.pazaramamovieapp.data.interceptor.RequestInterceptor
import com.example.pazaramamovieapp.data.repository.MovieRepositoryImpl
import com.example.pazaramamovieapp.data.service.MovieApi
import com.example.pazaramamovieapp.domain.repository.MovieRepository
import com.example.pazaramamovieapp.util.Constants.BASE_URL
import com.example.pazaramamovieapp.util.dispatcher.DefaultDispatcher
import com.example.pazaramamovieapp.util.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(
        okHttpClient: OkHttpClient
    ): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MovieApi
    ): MovieRepository {
        return MovieRepositoryImpl(movieApi)
    }

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider {
        return DefaultDispatcher()
    }
}