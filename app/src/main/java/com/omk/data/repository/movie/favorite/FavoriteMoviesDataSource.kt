package com.omk.data.repository.movie.favorite

import com.omk.data.entities.FavoriteMovieDbData
import com.omk.domain.util.Result

interface FavoriteMoviesDataSource {

    interface Local {
        suspend fun getFavoriteMovieIds(): Result<List<FavoriteMovieDbData>>
        suspend fun addMovieToFavorite(movieId: Int)
        suspend fun removeMovieFromFavorite(movieId: Int)
        suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean>
    }

}