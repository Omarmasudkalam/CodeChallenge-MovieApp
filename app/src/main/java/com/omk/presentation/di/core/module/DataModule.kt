package com.omk.presentation.di.core.module

import com.omk.data.api.MovieApi
import com.omk.data.db.favoritemovies.FavoriteMovieDao
import com.omk.data.db.movies.MovieDao
import com.omk.data.repository.movie.*
import com.omk.data.repository.movie.favorite.FavoriteMoviesDataSource
import com.omk.data.repository.movie.favorite.FavoriteMoviesLocalDataSource
import com.omk.data.util.DiskExecutor
import com.omk.data.util.DispatchersProvider
import com.omk.domain.repository.MovieRepository
import com.omk.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemote: MovieDataSource.Remote,
        movieLocal: MovieDataSource.Local,
        movieCache: MovieDataSource.Cache,
        favoriteLocal: FavoriteMoviesDataSource.Local,
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemote, movieLocal, movieCache, favoriteLocal)
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(
        executor: DiskExecutor, movieDao: MovieDao
    ): MovieDataSource.Local {
        return MovieLocalDataSource(executor, movieDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieLocalDataSource(
        executor: DiskExecutor,
        favoriteMovieDao: FavoriteMovieDao
    ): FavoriteMoviesDataSource.Local {
        return FavoriteMoviesLocalDataSource(executor, favoriteMovieDao)
    }

    @Provides
    @Singleton
    fun provideMovieCacheDataSource(diskExecutor: DiskExecutor): MovieDataSource.Cache {
        return MovieCacheDataSource(diskExecutor)
    }


    @Provides
    @Singleton
    fun provideMovieRemoveDataSource(movieApi: MovieApi, dispatchers: DispatchersProvider): MovieDataSource.Remote {
        return MovieRemoteDataSource(movieApi, dispatchers)
    }

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMovies {
        return GetMovies(movieRepository)
    }

    @Provides
    fun provideSearchMoviesUseCase(movieRepository: MovieRepository): SearchMovies {
        return SearchMovies(movieRepository)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository): GetMovieDetails {
        return GetMovieDetails(movieRepository)
    }

    @Provides
    fun provideGetFavoriteMoviesUseCase(movieRepository: MovieRepository): GetFavoriteMovies {
        return GetFavoriteMovies(movieRepository)
    }

    @Provides
    fun provideCheckFavoriteStatusUseCase(movieRepository: MovieRepository): CheckFavoriteStatus {
        return CheckFavoriteStatus(movieRepository)
    }

    @Provides
    fun provideAddMovieToFavoriteUseCase(movieRepository: MovieRepository): AddMovieToFavorite {
        return AddMovieToFavorite(movieRepository)
    }

    @Provides
    fun provideRemoveMovieFromFavoriteUseCase(movieRepository: MovieRepository): RemoveMovieFromFavorite {
        return RemoveMovieFromFavorite(movieRepository)
    }
}