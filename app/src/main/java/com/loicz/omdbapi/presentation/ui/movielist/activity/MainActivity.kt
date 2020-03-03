package com.loicz.omdbapi.presentation.ui.movielist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.loicz.omdbapi.R

class MainActivity : AppCompatActivity() {

    fun getNavHostId(): Int = R.id.main_nav_graph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
