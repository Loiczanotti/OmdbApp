package com.loicz.omdbapi.data.manager.service

import com.loicz.omdbapi.data.entity.MovieRemoteEntity
import com.loicz.omdbapi.data.entity.MovieResponseRemoteEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {


    @GET("?apikey=f180d375")
    fun searchMovieListFromTitle(
        @Query("s") title: String
    ): Single<MovieResponseRemoteEntity>

    @GET("?apikey=f180d375&plot=full")
    fun searchMovieById(@Query("i") id: String): Single<MovieRemoteEntity>


}