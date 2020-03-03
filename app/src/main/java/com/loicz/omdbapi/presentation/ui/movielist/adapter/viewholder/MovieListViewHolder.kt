package com.loicz.omdbapi.presentation.ui.movielist.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loicz.omdbapi.data.model.Movie
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bind(
        movie: Movie,
        onItemClickListener: (String) -> Unit
    ) {
        with(view) {
            Glide.with(context).load(movie.poster).into(item_movie_list_image)
            item_movie_list_title.text = movie.title
            item_movie_list_type.text = movie.type
            item_movie_list_year.text = movie.year
            setOnClickListener {
                onItemClickListener(movie.imdbId)
            }
        }
    }
}