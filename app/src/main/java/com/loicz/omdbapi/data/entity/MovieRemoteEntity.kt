package com.loicz.omdbapi.data.entity

import com.google.gson.annotations.SerializedName

data class MovieRemoteEntity (

    @SerializedName("Title") val title : String = "",
    @SerializedName("Year") val year : String = "",
    @SerializedName("imdbID") val imdbId : String = "",
    @SerializedName("Type") val type : String = "",
    @SerializedName("Poster") val poster : String = "",
    @SerializedName("Rated") val rated : String = "",
    @SerializedName("Actors") val actors : String = "",
    @SerializedName("Plot") val plot : String = "",
    @SerializedName("Released") val released : String = "",
    @SerializedName("imdbRating") val imdbRating : String = "",
    @SerializedName("Genre") val genre : String = "",
    @SerializedName("Runtime") val runtime : String = "",
    @SerializedName("BoxOffice") val boxOffice : String = ""

)