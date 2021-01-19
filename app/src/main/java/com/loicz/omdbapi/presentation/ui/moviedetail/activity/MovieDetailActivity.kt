package com.loicz.omdbapi.presentation.ui.moviedetail.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.loicz.omdbapi.R

class MovieDetailActivity : AppCompatActivity() {

    fun getNavHostId(): Int = R.id.movie_detail_activity_graph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        if (savedInstanceState == null) {
            findNavController(R.id.om_activity_movie_detail_fragment)
                .setGraph(R.navigation.movie_detail_activity_graph, intent.extras)
        }
    }
}