package com.omk.presentation.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omk.presentation.entities.MovieListItem
import com.omk.presentation.mapper.MovieEntityMapper
import com.omk.presentation.ui.base.BaseViewModel
import com.omk.presentation.util.SingleLiveEvent
import com.omk.data.util.DispatchersProvider
import com.omk.domain.entities.MovieEntity
import com.omk.domain.usecase.GetMovies
import com.omk.domain.util.onError
import com.omk.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject internal constructor(
    private val getMovies: GetMovies,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    sealed class NavigationState {
        data class MovieDetails(val movieId: Int) : NavigationState()
    }

    sealed class UiState {
        data class FeedUiState(val movies: List<MovieListItem>) : UiState()
        data class Error(val message: String?) : UiState()
        object Loading : UiState()
        object NotLoading : UiState()
    }

    private val uiState: MutableLiveData<UiState> = MutableLiveData()
    private val navigationState: SingleLiveEvent<NavigationState> = SingleLiveEvent()

    init {
        onInitialState()
    }

    fun onInitialState() = launchOnMainImmediate {
        loadMovies()
    }

    fun onMovieClicked(movieId: Int) = launchOnMainImmediate {
        navigationState.value = NavigationState.MovieDetails(movieId)
    }

    private suspend fun loadMovies() = launchOnMainImmediate {
        uiState.value = UiState.Loading
        getMovies.execute()
            .onSuccess {
                uiState.value = UiState.NotLoading
                uiState.value = UiState.FeedUiState(insertSeparators(it))
            }.onError {
                uiState.value = UiState.NotLoading
                uiState.value = UiState.Error(it.message)
            }
    }

    private fun insertSeparators(movies: List<MovieEntity>): List<MovieListItem> {
        var separator = "NONE"

        val listWithSeparators: ArrayList<MovieListItem> = arrayListOf()

        val sortedList = movies.sortedBy { it.type }

        sortedList.forEach { movie ->
            if (separator != movie.type) {
                listWithSeparators.add(MovieListItem.Separator(movie.type))
                separator = movie.type
            }
            listWithSeparators.add(MovieEntityMapper.toPresentation(movie))
        }

        return listWithSeparators
    }


    fun getNavigationState(): LiveData<NavigationState> = navigationState
    fun getUiState(): LiveData<UiState> = uiState

    class Factory(
        private val getMovies: GetMovies,
        private val dispatchers: DispatchersProvider
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedViewModel(getMovies, dispatchers) as T
        }
    }
}