package com.loicz.omdbapi.presentation.ui.movielist.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loicz.omdbapi.data.model.Movie
import com.loicz.omdbapi.data.repository.MovieRepository
import com.loicz.omdbapi.presentation.ui.movielist.viewmodel.viewstate.MovieListFragmentViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MovieListFragmentViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val viewState: MutableLiveData<MovieListFragmentViewState> = MutableLiveData()

    private val currentViewState = MovieListFragmentViewState()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun updateRefresh(isRefreshing: Boolean) {
        viewState.postValue(currentViewState.apply {
            displayRefresh = isRefreshing
        })
    }

    fun searchMovieListFromTitle(title: String) {
        compositeDisposable.add(movieRepository.searchMovieFromTitle(title)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { list ->
                    movieList.postValue(list)
                    updateRefresh(false)
                },
                onError = {
                    errorLiveData.postValue(it)
                }
            ))
    }

}