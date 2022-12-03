package com.omk.domain.usecase

import com.omk.domain.repository.MovieRepository
import com.omk.domain.util.Result


class CheckFavoriteStatus(
    private val movieRepository: MovieRepository
) {
    suspend fun check(movieId: Int): Result<Boolean> = movieRepository.checkFavoriteStatus(movieId)
}