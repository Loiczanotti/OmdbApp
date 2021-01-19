package com.loicz.omdbapi.data.manager

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.loicz.omdbapi.data.entity.MovieRemoteEntity
import com.loicz.omdbapi.data.entity.MovieResponseRemoteEntity
import com.loicz.omdbapi.data.manager.service.RetrofitApiService
import com.loicz.omdbapi.data.model.Movie
import io.reactivex.Single
import retrofit2.Retrofit

class MovieManagerImpl(retrofit: Retrofit) : MovieManager {

    private val apiService = retrofit.create(RetrofitApiService::class.java)


    override fun searchMovieListFromTitle(title: String): Single<MovieResponseRemoteEntity> =
        apiService.searchMovieListFromTitle(title)

    override fun searchMovieById(id: String): Single<MovieRemoteEntity> =
        apiService.searchMovieById(id)

    override fun saveMovie(movie: Movie) {
        val database = Firebase.database.reference
        database.child("movies").child(movie.imdbId).setValue(movie)
    }

}