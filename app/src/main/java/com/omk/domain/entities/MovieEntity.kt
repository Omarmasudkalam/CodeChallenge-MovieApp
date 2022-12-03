package com.omk.domain.entities


data class MovieEntity(
    val id: Int,
    val name: String,
    val summary: String,
    val image: String,
    val type: String
)