package com.loicz.omdbapi.data.manager

import com.loicz.omdbapi.data.entity.MovieRemoteEntity
import com.loicz.omdbapi.data.entity.MovieResponseRemoteEntity
import io.reactivex.Single

interface MovieManager {

    fun searchMovieListFromTitle(title: String) : Single<MovieResponseRemoteEntity>
    fun searchMovieById(id: String): Single<MovieRemoteEntity>

}