package com.example.spatsapplication.services.helper

import android.content.Context
import com.example.spatsapplication.models.response.SpotifyUserCurrentTrackResponse
import com.example.spatsapplication.models.response.SpotifyUserProfileResponse
import com.example.spatsapplication.services.UserInfoService
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpotifyProfileDataHelper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userInfoService: UserInfoService
) {

    fun fetchSpotifyUserProfile(): Observable<SpotifyUserProfileResponse> {
        val preferences = context.getSharedPreferences("LegatoPrefs", Context.MODE_PRIVATE)
        val accessToken = preferences.getString("spotify_access_token", null)

        if (accessToken == null) {
            return Observable.error(Throwable("Missing Spotify access token"))
        }

        val authHeader = "Bearer $accessToken"

        return userInfoService
            .getUserProfileData(authHeader)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchSpotifyUserLastTrackPlayed(): Observable<SpotifyUserCurrentTrackResponse> {
        val preferences = context.getSharedPreferences("LegatoPrefs", Context.MODE_PRIVATE)
        val accessToken = preferences.getString("spotify_access_token", null)

        if (accessToken == null) {
            return Observable.error(Throwable("Missing Spotify access token"))
        }

        val authHeader = "Bearer $accessToken"

        return userInfoService
            .getUserRecentlyPlayedTracks(authHeader)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}