package com.omk.data.entities.search

data class ShowSearch(
    val id: Int = 0,
    val image: Image = Image(),
    val name: String = "",
    val premiered: String = "",
    val rating: Rating = Rating(),
    val runtime: Int? = 0,
    val status: String = "",
    val summary: String = "",
    val type: String = "",
)