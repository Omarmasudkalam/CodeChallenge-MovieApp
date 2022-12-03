package com.omk.presentation.ui.favorites

import androidx.lifecycle.*
import com.omk.data.exception.DataNotAvailableException
import com.omk.data.util.DispatchersProvider
import com.omk.domain.entities.MovieEntity
import com.omk.domain.usecase.GetFavoriteMovies
import com.omk.domain.util.onError
import com.omk.domain.util.onSuccess
import com.omk.presentation.entities.MovieListItem
import com.omk.presentation.mapper.MovieEntityMapper
import com.omk.presentation.ui.base.BaseViewModel
import com.omk.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject internal constructor(
    private val getFavoriteMovies: GetFavoriteMovies,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers), DefaultLifecycleObserver {

    data class FavoriteUiState(
        val isLoading: Boolean = false,
        val noDataAvailable: Boolean = false,
        val movies: List<MovieListItem> = emptyList()
    )

    sealed class NavigationState {
        data class MovieDetails(val movieId: Int) : NavigationState()
    }

    private val favoriteUiState: MutableLiveData<FavoriteUiState> = MutableLiveData()
    private val navigationState: SingleLiveEvent<NavigationState> = SingleLiveEvent()

    override fun onResume(owner: LifecycleOwner) {
        onResumeInternal()
    }

    private fun onResumeInternal() = launchOnMainImmediate {
        loadMovies()
    }

    private suspend fun loadMovies() {
        favoriteUiState.value = FavoriteUiState(isLoading = true)

        getFavoriteMovies()
            .onSuccess {
                showData(it)
            }.onError {
                when (it) {
                    is DataNotAvailableException -> showNoData()
                    else -> favoriteUiState.value = favoriteUiState.value?.copy(isLoading = false)
                }
            }
    }

    private fun showData(list: List<MovieEntity>) {
        favoriteUiState.value = favoriteUiState.value?.copy(
            isLoading = false,
            noDataAvailable = false,
            movies = list.map { movieEntity -> MovieEntityMapper.toPresentation(movieEntity) }
        )
    }

    private fun showNoData() {
        favoriteUiState.value = favoriteUiState.value?.copy(
            isLoading = false,
            noDataAvailable = true,
            movies = emptyList()
        )
    }

    private suspend fun getFavoriteMovies() = getFavoriteMovies.getFavoriteMovies()

    fun onMovieClicked(movieId: Int) = launchOnMainImmediate {
        navigationState.value = NavigationState.MovieDetails(movieId)
    }

    fun getFavoriteUiState(): LiveData<FavoriteUiState> = favoriteUiState
    fun getNavigateState(): LiveData<NavigationState> = navigationState

    class Factory(
        private val getFavoriteMovies: GetFavoriteMovies,
        private val dispatchers: DispatchersProvider
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavoritesViewModel(getFavoriteMovies, dispatchers) as T
        }
    }
}