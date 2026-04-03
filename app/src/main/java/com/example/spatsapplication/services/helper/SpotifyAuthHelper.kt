package com.example.spatsapplication.services.helper

import android.content.Context
import android.util.Base64
import android.util.Log
import com.example.spatsapplication.R
import com.example.spatsapplication.models.response.SpotifyAccessTokenResponse
import com.example.spatsapplication.services.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpotifyAuthHelper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authService: AuthService
) {

    fun fetchAccessToken(authToken: String): Observable<SpotifyAccessTokenResponse> {
        val credentials =
            context.getString(R.string.SPOTIFY_CLIENT_ID) + ":" + context.getString(R.string.SPOTIFY_CLIENT_SECRET)
        val authHeader =
            "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        return authService
            .getAccessToken(
                "authorization_code",
                authToken,
                context.getString(R.string.REDIRECT_URI),
                authHeader
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun refreshAccessToken(): Observable<SpotifyAccessTokenResponse> {
        val prefs = context.getSharedPreferences("LegatoPrefs", Context.MODE_PRIVATE)
        val refreshToken = prefs.getString("spotify_refresh_token", null)

        if (refreshToken == null) {
            Log.w("SpotifyAuthentication", "Refresh token is not stored yet or is null. . .")
            return Observable.error(Throwable("No refresh token stored. . ."))
        }

        val credentials =
            context.getString(R.string.SPOTIFY_CLIENT_ID) + ":" + context.getString(R.string.SPOTIFY_CLIENT_SECRET)
        val authHeader =
            "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        return authService
            .refreshAccessToken("refresh_token", refreshToken, authHeader)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun storeSpotifyTokens(accessToken: String, refreshToken: String) {
        val preferences = context.getSharedPreferences("LegatoPrefs", Context.MODE_PRIVATE)

        preferences.edit()
            .putString("spotify_access_token", accessToken)
            .putString("spotify_refresh_token", refreshToken)
            .putBoolean("isSpotifyTokenReceived", true)
            .apply()
    }
}