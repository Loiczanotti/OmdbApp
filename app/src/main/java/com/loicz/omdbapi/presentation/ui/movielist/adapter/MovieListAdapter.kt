package com.loicz.omdbapi.presentation.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loicz.omdbapi.R
import com.loicz.omdbapi.data.model.Movie
import com.loicz.omdbapi.presentation.extension.replace
import com.loicz.omdbapi.presentation.ui.movielist.adapter.viewholder.MovieListViewHolder

class MovieListAdapter : RecyclerView.Adapter<MovieListViewHolder>() {

    private val movieList = mutableListOf<Movie>()
    var onItemClickListener: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        )
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movieList[position], onItemClickListener)
    }

    fun updateList(list: List<Movie>) {
        this.movieList.replace(list)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.movieList.clear()
        notifyDataSetChanged()
    }
}