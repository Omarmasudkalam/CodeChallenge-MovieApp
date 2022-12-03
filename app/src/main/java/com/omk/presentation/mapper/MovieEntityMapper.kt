package com.omk.presentation.mapper

import com.omk.presentation.entities.MovieListItem
import com.omk.domain.entities.MovieEntity


object MovieEntityMapper {

    fun toPresentation(movieEntity: MovieEntity) = MovieListItem.Movie(
        id = movieEntity.id,
        imageUrl = movieEntity.image
    )
}