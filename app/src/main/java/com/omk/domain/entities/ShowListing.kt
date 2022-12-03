package com.omk.domain.entities

import com.omk.data.entities.search.Image

data class ShowListing(
    val id: Int,
    val name: String,
    val type: String,
    val image: Image,
    val summary: String

)
