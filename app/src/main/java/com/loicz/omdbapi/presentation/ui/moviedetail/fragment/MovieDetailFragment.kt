package com.loicz.omdbapi.presentation.ui.moviedetail.fragment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.loicz.omdbapi.R
import com.loicz.omdbapi.data.model.Movie
import com.loicz.omdbapi.presentation.component.SnackBarComponent
import com.loicz.omdbapi.presentation.extension.observeSafe
import com.loicz.omdbapi.presentation.ui.base.BaseFragment
import com.loicz.omdbapi.presentation.ui.movielist.viewmodel.MovieDetailFragmentViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailFragment : BaseFragment() {

    private val args by navArgs<MovieDetailFragmentArgs>()
    private val movieDetailFragmentViewModel: MovieDetailFragmentViewModel by viewModel()
    private val snackBarComponent: SnackBarComponent by inject()

    override fun layoutId(): Int = R.layout.fragment_movie_detail

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.imdbId.isNotBlank()) {
            movieDetailFragmentViewModel.retrieveMovieById(args.imdbId)
        } else {
            activity?.finish()
        }
        initLiveDataObservers()
    }


    private fun initLiveDataObservers() {
        movieDetailFragmentViewModel.movieLiveData.observeSafe(this) {
            bindItemView(it)
        }

        movieDetailFragmentViewModel.errorLiveData.observeSafe(this) {
            it.message?.let { message ->
                view?.let { view ->
                    snackBarComponent.showSnackBarError(
                        view,
                        message,
                        requireContext()
                    )
                }
            }
        }

        movieDetailFragmentViewModel.savedMovieLiveData.observeSafe(this) {
            snackBarComponent.showSnackBarSuccess(
                fragment_movie_detail_container,
                "Film enregistré avec Succès !",
                requireContext()
            )
        }
    }

    private fun bindItemView(movie: Movie) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragment_movie_detail_toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = movie.title
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        setHasOptionsMenu(true)

        om_fragment_movie_detail_title.text = movie.title
        om_fragment_movie_detail_boxoffice.text = movie.boxOffice
        om_fragment_movie_detail_rating.text = movie.rated
        om_fragment_movie_detail_synopsis.text = movie.plot
        om_fragment_movie_detail_released.text =
            requireContext().getString(R.string.released_date, movie.released)
        Glide.with(requireContext()).load(movie.poster).into(om_fragment_movie_detail_image)

        om_fragment_movie_detail_save_movie.setOnClickListener {
            movieDetailFragmentViewModel.saveMovie(movie)
        }
    }
}