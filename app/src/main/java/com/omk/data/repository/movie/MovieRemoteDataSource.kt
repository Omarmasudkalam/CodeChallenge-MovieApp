package com.omk.data.repository.movie

import com.omk.data.api.MovieApi
import com.omk.data.mapper.MovieDataMapper
import com.omk.data.util.DispatchersProvider
import com.omk.domain.entities.MovieEntity
import com.omk.domain.util.Result
import kotlinx.coroutines.withContext


class MovieRemoteDataSource(
    private val movieApi: MovieApi,
    private val dispatchers: DispatchersProvider
) : MovieDataSource.Remote {

    override suspend fun getMovies(): Result<List<MovieEntity>> = withContext(dispatchers.getIO()) {
        return@withContext try {
            val result = movieApi.getMovies()
            Result.Success(result.map {
                MovieDataMapper.toDomain(it)
            })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun search(query: String): Result<List<MovieEntity>> =
        withContext(dispatchers.getIO()) {
            return@withContext try {
                val result = movieApi.search(query)
                Result.Success(result.map {
                    MovieDataMapper.toDomain(it)
                })
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}