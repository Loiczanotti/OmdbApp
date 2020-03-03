package com.loicz.omdbapi.data.entity

import com.google.gson.annotations.SerializedName

data class MovieResponseRemoteEntity (
    @SerializedName("Search")
    val movieList : List<MovieRemoteEntity> = emptyList()
)