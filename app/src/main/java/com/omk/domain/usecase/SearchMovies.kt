package com.omk.domain.usecase

import com.omk.domain.entities.MovieEntity
import com.omk.domain.repository.MovieRepository
import com.omk.domain.util.Result


class SearchMovies(
    private val movieRepository: MovieRepository
) {
    //suspend fun search(query: String): Result<List<MovieEntity>> = movieRepository.search(query)
}
