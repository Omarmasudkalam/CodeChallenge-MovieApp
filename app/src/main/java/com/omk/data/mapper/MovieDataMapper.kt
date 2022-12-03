package com.omk.data.mapper

import com.omk.data.entities.showData.MovieItem
import com.omk.data.entities.MovieDbData
import com.omk.domain.entities.MovieEntity


object MovieDataMapper {

    fun toDomain(movieData: MovieItem): MovieEntity = MovieEntity(
        id = movieData.id,
        image = movieData.image.medium,
        summary = movieData.summary,
        name = movieData.name,
        type = movieData.type
    )

    fun toDomain(movieDbData: MovieDbData): MovieEntity = MovieEntity(
        id = movieDbData.id,
        image = movieDbData.image,
        summary = movieDbData.description,
        name = movieDbData.title,
        type = movieDbData.category
    )

    fun toDbData(movieEntity: MovieEntity): MovieDbData = MovieDbData(
        id = movieEntity.id,
        image = movieEntity.image,
        description = movieEntity.summary,
        title = movieEntity.name,
        category = movieEntity.type
    )
}