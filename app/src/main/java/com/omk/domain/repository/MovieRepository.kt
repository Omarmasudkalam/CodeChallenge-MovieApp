package com.omk.domain.repository

import com.omk.domain.entities.MovieEntity
import com.omk.domain.util.Result


interface MovieRepository {
    suspend fun getMovies(): Result<List<MovieEntity>>
   // suspend fun search(query: String): Result<List<MovieEntity>>
    suspend fun getMovie(movieId: Int): Result<MovieEntity>
    suspend fun getFavoriteMovies(): Result<List<MovieEntity>>
    suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean>
    suspend fun addMovieToFavorite(movieId: Int)
    suspend fun removeMovieFromFavorite(movieId: Int)
}