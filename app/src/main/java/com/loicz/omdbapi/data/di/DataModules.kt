package com.loicz.omdbapi.data.di

import com.loicz.omdbapi.data.entity.mapper.MovieRemoteEntityDataMapper
import com.loicz.omdbapi.data.manager.MovieManager
import com.loicz.omdbapi.data.manager.MovieManagerImpl
import com.loicz.omdbapi.data.repository.MovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://www.omdbapi.com/"

val apiModule = module {

    single {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .client(get()).build()
    }

    single<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }
    single<Converter.Factory> { GsonConverterFactory.create() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

}

val repositoryModule = module {
    single { MovieRepository(get(), get()) }
}

val managerModule = module {
    single<MovieManager> { MovieManagerImpl(get()) }
}

val mapperModule = module {
    single { MovieRemoteEntityDataMapper() }
}

val dataModules = apiModule + managerModule + repositoryModule + mapperModule



