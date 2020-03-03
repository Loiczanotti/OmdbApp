package com.loicz.omdbapi.presentation.di

import androidx.navigation.findNavController
import com.loicz.omdbapi.presentation.ui.movielist.activity.MainActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainActivityModule = module {

    scope(named<MainActivity>()) {

        scoped { (activity: MainActivity) ->
            activity.findNavController(activity.getNavHostId())
        }

    }
}