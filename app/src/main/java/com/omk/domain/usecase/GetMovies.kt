package com.omk.domain.usecase

import com.omk.domain.entities.MovieEntity
import com.omk.domain.repository.MovieRepository
import com.omk.domain.util.Result


open class GetMovies(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(): Result<List<MovieEntity>> = movieRepository.getMovies()
}
