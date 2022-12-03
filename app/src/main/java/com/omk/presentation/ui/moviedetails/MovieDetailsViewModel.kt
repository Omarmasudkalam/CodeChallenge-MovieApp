package com.omk.presentation.ui.moviedetails

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omk.clean.R
import com.omk.data.util.DispatchersProvider
import com.omk.domain.entities.MovieEntity
import com.omk.domain.usecase.AddMovieToFavorite
import com.omk.domain.usecase.CheckFavoriteStatus
import com.omk.domain.usecase.GetMovieDetails
import com.omk.domain.usecase.RemoveMovieFromFavorite
import com.omk.domain.util.Result
import com.omk.domain.util.onSuccess
import com.omk.presentation.ui.base.BaseViewModel
import com.omk.presentation.util.ResourceProvider
import javax.inject.Inject


class MovieDetailsViewModel internal constructor(
    private var movieId: Int,
    private val getMovieDetails: GetMovieDetails,
    private val checkFavoriteStatus: CheckFavoriteStatus,
    private val addMovieToFavorite: AddMovieToFavorite,
    private val removeMovieFromFavorite: RemoveMovieFromFavorite,
    private val resourceProvider: ResourceProvider,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    data class FavoriteState(val drawable: Drawable?)

    data class MovieDetailsUiState(
        val title: String,
        val description: String,
        val imageUrl: String
    )

    private val movieDetailsUiState: MutableLiveData<MovieDetailsUiState> = MutableLiveData()
    private val favoriteState: MutableLiveData<FavoriteState> = MutableLiveData()

    init {
        onInitialState()
    }

    fun onInitialState() = launchOnMainImmediate {
        getMovieById(movieId).onSuccess {
            movieDetailsUiState.value = MovieDetailsUiState(
                title = it.name,
                description = it.summary,
                imageUrl = it.image,
            )

            checkFavoriteStatus(movieId).onSuccess { isFavorite ->
                favoriteState.value = FavoriteState(getFavoriteDrawable(isFavorite))
            }
        }
    }

    fun onFavoriteClicked() = launchOnMainImmediate {
        checkFavoriteStatus(movieId).onSuccess {
            if (it) removeMovieFromFavorite.remove(movieId) else addMovieToFavorite.add(movieId)
            favoriteState.value = FavoriteState(getFavoriteDrawable(!it))
        }
    }

    private fun getFavoriteDrawable(favorite: Boolean): Drawable? = if (favorite) {
        resourceProvider.getDrawable(R.drawable.ic_favorite_fill_white_48)
    } else {
        resourceProvider.getDrawable(R.drawable.ic_favorite_border_white_48)
    }

    private suspend fun getMovieById(movieId: Int): Result<MovieEntity> = getMovieDetails.getMovie(movieId)

    private suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean> = checkFavoriteStatus.check(movieId)

    fun getMovieDetailsUiStateLiveData(): LiveData<MovieDetailsUiState> = movieDetailsUiState
    fun getFavoriteStateLiveData(): LiveData<FavoriteState> = favoriteState

    class Factory @Inject constructor(
        private val getMovieDetails: GetMovieDetails,
        private val checkFavoriteStatus: CheckFavoriteStatus,
        private val addMovieToFavorite: AddMovieToFavorite,
        private val removeMovieFromFavorite: RemoveMovieFromFavorite,
        private val resourceProvider: ResourceProvider,
        private val dispatchers: DispatchersProvider
    ) : ViewModelProvider.Factory {

        var movieId: Int = 0

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(
                movieId = movieId,
                getMovieDetails = getMovieDetails,
                checkFavoriteStatus = checkFavoriteStatus,
                addMovieToFavorite = addMovieToFavorite,
                removeMovieFromFavorite = removeMovieFromFavorite,
                resourceProvider = resourceProvider,
                dispatchers = dispatchers
            ) as T
        }
    }
}