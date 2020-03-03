package com.loicz.omdbapi.presentation.di

import com.loicz.omdbapi.presentation.component.SnackBarComponent
import com.loicz.omdbapi.presentation.component.SnackBarComponentImpl
import com.loicz.omdbapi.presentation.ui.movielist.adapter.MovieListAdapter
import com.loicz.omdbapi.presentation.ui.movielist.viewmodel.MovieDetailFragmentViewModel
import com.loicz.omdbapi.presentation.ui.movielist.viewmodel.MovieListFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { MovieListFragmentViewModel(get()) }
    viewModel { MovieDetailFragmentViewModel(get()) }
}

private val adapterModule = module {
    factory { MovieListAdapter() }
}

private val applicationModule = module {
    factory { SnackBarComponentImpl() as SnackBarComponent }
}

private val activitiesModule = arrayOf(mainActivityModule, movieDetailActivityModule)

val presentationModules = activitiesModule + viewModelModule + adapterModule + applicationModule