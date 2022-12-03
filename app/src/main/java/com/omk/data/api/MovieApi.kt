package com.omk.data.api

import com.omk.data.entities.showData.MovieItem
import com.omk.data.entities.search.ShowSearch
import com.omk.domain.entities.ShowListing
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
    @GET(SHOW_END)
    suspend fun getMovies(): List<MovieItem>

    @GET(SINGLE_SEARCH_END)
    suspend fun search (@Query("q") query: String): List<MovieItem>

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
        const val SHOW_END = "shows"
        const val SINGLE_SEARCH_END = "singlesearch/shows"
    }

}


