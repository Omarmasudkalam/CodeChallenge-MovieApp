package com.omk.domain.usecase

import com.omk.domain.entities.MovieEntity
import com.omk.domain.repository.MovieRepository
import com.omk.domain.util.Result


class GetMovieDetails(
    private val movieRepository: MovieRepository
) {
    suspend fun getMovie(movieId: Int): Result<MovieEntity> = movieRepository.getMovie(movieId)
}
