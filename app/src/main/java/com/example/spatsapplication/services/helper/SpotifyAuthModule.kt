package com.example.spatsapplication.di

import android.content.Context
import com.example.spatsapplication.R
import com.example.spatsapplication.services.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpotifyAuthModule {

    @Provides
    @Singleton
    fun provideAuthService(
        @ApplicationContext context: Context
    ): AuthService {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(AuthService::class.java)
    }
}