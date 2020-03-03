package com.loicz.omdbapi.data.entity.mapper

import com.loicz.omdbapi.data.entity.MovieRemoteEntity
import com.loicz.omdbapi.data.exception.MappingException
import com.loicz.omdbapi.data.model.Movie

class MovieRemoteEntityDataMapper {

    fun transformFromRemoteEntity(movieRemoteEntity: MovieRemoteEntity): Movie {
        return Movie(
            title = movieRemoteEntity.title,
            year = movieRemoteEntity.year,
            imdbId = movieRemoteEntity.imdbId,
            poster = movieRemoteEntity.poster,
            type = movieRemoteEntity.type,
            actors = movieRemoteEntity.actors,
            boxOffice = movieRemoteEntity.boxOffice,
            genre = movieRemoteEntity.genre,
            imdbRating = movieRemoteEntity.imdbRating,
            plot = movieRemoteEntity.plot,
            rated = movieRemoteEntity.rated,
            released = movieRemoteEntity.released,
            runtime = movieRemoteEntity.runtime
        )
    }

    fun transformFromRemoteEntityList(movieRemoteEntityList: List<MovieRemoteEntity>): List<Movie> {
        return movieRemoteEntityList.map {
            try {
                transformFromRemoteEntity(it)
            } catch (e: MappingException) {
                throw MappingException()
            }
        }
    }

}