package com.omk.domain.usecase

import com.omk.domain.repository.MovieRepository


class RemoveMovieFromFavorite(
    private val movieRepository: MovieRepository
) {
    suspend fun remove(movieId: Int) = movieRepository.removeMovieFromFavorite(movieId)
}