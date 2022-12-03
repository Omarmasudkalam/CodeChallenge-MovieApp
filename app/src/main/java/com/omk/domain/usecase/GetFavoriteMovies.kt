package com.omk.domain.usecase

import com.omk.domain.entities.MovieEntity
import com.omk.domain.repository.MovieRepository
import com.omk.domain.util.Result


class GetFavoriteMovies(
    private val movieRepository: MovieRepository
) {
    suspend fun getFavoriteMovies(): Result<List<MovieEntity>> = movieRepository.getFavoriteMovies()
}