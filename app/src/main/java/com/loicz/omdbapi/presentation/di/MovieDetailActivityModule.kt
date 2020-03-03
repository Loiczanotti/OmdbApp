package com.loicz.omdbapi.presentation.di

import androidx.navigation.findNavController
import com.loicz.omdbapi.presentation.ui.movielist.activity.MovieDetailActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieDetailActivityModule = module {

    scope(named<MovieDetailActivity>()) {

        scoped { (activity: MovieDetailActivity) ->
            activity.findNavController(activity.getNavHostId())
        }
    }
}