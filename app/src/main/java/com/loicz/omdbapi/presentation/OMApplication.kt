package com.loicz.omdbapi.presentation

import android.app.Application
import com.loicz.omdbapi.data.di.dataModules
import com.loicz.omdbapi.presentation.di.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OMApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OMApplication)
            modules((presentationModules + dataModules).toList())
        }

    }
}