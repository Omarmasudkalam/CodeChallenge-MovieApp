package com.omk.domain.usecase

import com.omk.domain.repository.MovieRepository

class AddMovieToFavorite(
    private val movieRepository: MovieRepository
) {
    suspend fun add(movieId: Int) = movieRepository.addMovieToFavorite(movieId)
}