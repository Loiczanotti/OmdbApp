package com.loicz.omdbapi.presentation.ui.movielist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loicz.omdbapi.data.model.Movie
import com.loicz.omdbapi.data.repository.MovieRepository
import com.loicz.omdbapi.presentation.ui.movielist.viewmodel.viewstate.MovieDetailFragmentViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MovieDetailFragmentViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val movieLiveData: MutableLiveData<Movie> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val viewState: MutableLiveData<MovieDetailFragmentViewState> = MutableLiveData()

    private val currentViewState = MovieDetailFragmentViewState()

    fun retrieveMovieById(id: String) {
        compositeDisposable.add(
            movieRepository.searchMovieById(id)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = movieLiveData::postValue,
                    onError = errorLiveData::postValue
                )
        )
    }
}