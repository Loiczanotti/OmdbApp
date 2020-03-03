package com.loicz.omdbapi.presentation.extension

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> MutableCollection<T>.replace(list: List<T>) {
    clear()
    addAll(list)
}

fun <T> LiveData<T>.observeSafe(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner, Observer<T> { t ->
        t?.let(observer)
    })
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
