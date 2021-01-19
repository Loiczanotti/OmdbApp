package com.loicz.omdbapi.presentation.component

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class SnackBarComponentImpl: SnackBarComponent {

    override fun showSnackBarError(view: View, message: String, context: Context) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        snackbar.show()
    }

    override fun showSnackBarSuccess(view: View, message: String, context: Context) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        snackbar.show()
    }
}