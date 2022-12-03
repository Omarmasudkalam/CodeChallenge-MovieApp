package com.omk.presentation.entities

sealed class MovieListItem {
    data class Movie(
        val id: Int,
        val imageUrl: String,
    ) : MovieListItem()

    data class Separator(val category: String) : MovieListItem()
}
