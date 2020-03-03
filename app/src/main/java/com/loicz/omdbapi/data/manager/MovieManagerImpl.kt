package com.loicz.omdbapi.data.manager

import com.loicz.omdbapi.data.entity.MovieRemoteEntity
import com.loicz.omdbapi.data.entity.MovieResponseRemoteEntity
import com.loicz.omdbapi.data.manager.service.RetrofitApiService
import io.reactivex.Single
import retrofit2.Retrofit

class MovieManagerImpl(retrofit: Retrofit) : MovieManager {

    private val apiService = retrofit.create(RetrofitApiService::class.java)


    override fun searchMovieListFromTitle(title: String): Single<MovieResponseRemoteEntity> =
        apiService.searchMovieListFromTitle(title)

    override fun searchMovieById(id: String): Single<MovieRemoteEntity> =
        apiService.searchMovieById(id)

}