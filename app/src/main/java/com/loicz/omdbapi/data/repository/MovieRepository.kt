package com.loicz.omdbapi.data.repository

import com.loicz.omdbapi.data.entity.mapper.MovieRemoteEntityDataMapper
import com.loicz.omdbapi.data.manager.MovieManager
import com.loicz.omdbapi.data.model.Movie
import io.reactivex.Single

class MovieRepository(
    private val movieRemoteEntityDataMapper: MovieRemoteEntityDataMapper,
    private val movieManager: MovieManager
) {

    fun searchMovieFromTitle(title: String): Single<List<Movie>> {
       return movieManager.searchMovieListFromTitle(title).map {
           movieRemoteEntityDataMapper.transformFromRemoteEntityList(it.movieList)
       }
    }

    fun searchMovieById(id: String): Single<Movie> {
        return movieManager.searchMovieById(id).map {
            movieRemoteEntityDataMapper.transformFromRemoteEntity(it)
        }
    }
}