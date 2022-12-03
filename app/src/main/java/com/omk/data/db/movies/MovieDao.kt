package com.omk.data.db.movies


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omk.data.entities.MovieDbData


@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): List<MovieDbData>


    @Query("SELECT * FROM movies WHERE id IN (:movieIds)")
    fun getFavoriteMovies(movieIds: List<Int>): List<MovieDbData>


    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovie(movieId: Int): MovieDbData?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<MovieDbData>)


    @Query("DELETE FROM movies")
    fun deleteMovies()
}