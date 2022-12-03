package com.omk.data.api

import com.omk.data.entities.ShowEntity.ShowItem
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
    @GET(SHOW_END)
    suspend fun getMovies(): List<ShowItem>

    @GET(SHOW_END)
    suspend fun search (@Query("q") query: String): List<ShowItem>

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
        const val SHOW_END = "shows"
    }

}


