package com.jacktich.meetingnotifications.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import com.jacktich.meetingnotifications.utils.constants.Urls
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideApiBuilder(): Retrofit {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient =
            OkHttpClient.Builder()
                .addInterceptor(logging)
        val converterFactory = GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .client(httpClient.build())
            .baseUrl(Urls.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()


}