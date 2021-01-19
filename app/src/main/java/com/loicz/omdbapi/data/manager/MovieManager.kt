package com.loicz.omdbapi.data.manager

import com.loicz.omdbapi.data.entity.MovieRemoteEntity
import com.loicz.omdbapi.data.entity.MovieResponseRemoteEntity
import com.loicz.omdbapi.data.model.Movie
import io.reactivex.Single

interface MovieManager {

    fun searchMovieListFromTitle(title: String) : Single<MovieResponseRemoteEntity>
    fun searchMovieById(id: String): Single<MovieRemoteEntity>
    fun saveMovie(movie: Movie)

}